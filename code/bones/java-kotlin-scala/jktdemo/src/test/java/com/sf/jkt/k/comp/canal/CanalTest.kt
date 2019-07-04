package com.sf.jkt.k.comp.canal

import com.alibaba.otter.canal.client.CanalConnector
import com.alibaba.otter.canal.client.CanalConnectors
import com.alibaba.otter.canal.protocol.CanalEntry
import java.net.InetSocketAddress
import java.util.concurrent.TimeUnit


fun testCanalClient() {
    val connector = CanalConnectors.newSingleConnector(InetSocketAddress("127.0.0.1", 11111),
            "example", "", "")
    connector.connect()
    connector.subscribe()
    while (true) {
        try {
            val message = connector.getWithoutAck(1000)
            val batchId = message.id
            val size = message.entries.size
            if (batchId == -1L || size == 0) {
                println("当前暂无数据")
                TimeUnit.SECONDS.sleep(1)
            } else {
                println("***************有数据了***************")
                printEntry(message.entries)
            }
            connector.ack(batchId)
        } catch (e: Exception) {
            //handle Exception
            e.printStackTrace()
        } finally {
            TimeUnit.SECONDS.sleep(1)
        }

    }

}

fun printEntry(entries: List<CanalEntry.Entry>) {
    entries.forEach { it ->
        val header = it.header
        val entryType = it.entryType
        if (entryType == CanalEntry.EntryType.ROWDATA) {
            val tableName = header.tableName
            val schemaName = header.schemaName
            var rowChange: CanalEntry.RowChange? = null
            try {
                rowChange = CanalEntry.RowChange.parseFrom(it.storeValue)
            } catch (e: Exception) {
                //handleException
                e.printStackTrace()
            }
            val eventType = rowChange?.eventType
            println(String.format("当前正在操作%s.%s,Action=%s", schemaName, tableName, eventType))

            if (eventType == CanalEntry.EventType.QUERY || rowChange!!.isDdl) {
                println("rowChange sql------->" + rowChange?.sql)
                return
            }
            rowChange.rowDatasList.forEach { rowData ->
                val beforeColumns = rowData.beforeColumnsList
                val afterColumns = rowData.afterColumnsList
                if (eventType == CanalEntry.EventType.DELETE) {
                    printColumn(beforeColumns)
                }
                if (eventType == CanalEntry.EventType.INSERT) {
                    printColumn(afterColumns)
                }
                if (eventType == CanalEntry.EventType.UPDATE) {
                    printColumn(afterColumns)
                }
            }

        }
    }
}

fun printColumn(columns: List<CanalEntry.Column>) {
    columns.forEach { it ->
        println(String.format(" columnName=%s,columnValue=%s,columnType=%s," +
                "isUpdated=%s", it.name, it.value,it.mysqlType, it.updated))
    }
}

fun main() {
    testCanalClient()
}