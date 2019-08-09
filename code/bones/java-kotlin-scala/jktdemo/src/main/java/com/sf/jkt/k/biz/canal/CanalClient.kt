package com.sf.jkt.k.biz.canal

import com.alibaba.otter.canal.client.CanalConnectors
import com.alibaba.otter.canal.protocol.CanalEntry
import java.lang.RuntimeException
import java.net.InetSocketAddress
import java.util.concurrent.TimeUnit

fun main() {
    testCanalClient()
}

fun testCanalClient() {
    val host = "127.0.0.1"
    val port = 11111
    val connector = CanalConnectors.newSingleConnector(InetSocketAddress(host, port), "example", "", "")
    val batchSize = 1000
    var emptyCount = 0
    var batchId = -1L
    try {
        connector.connect()
        connector.subscribe(".*\\..*")
        connector.rollback()
        val totalEntryCount = 1200
        while (emptyCount < totalEntryCount) {
            val message = connector.getWithoutAck(batchSize)
            batchId = message.id
            val size = message.entries.size
            if (batchId == -1L || size == 0) {
                emptyCount++
                println("empty count:" + emptyCount)
                TimeUnit.SECONDS.sleep(5)
            } else {
                emptyCount = 0
                println(message)
                printEntry(message.entries)
            }
            connector.ack(batchId)
        }
        println("empty too many times,exit")
    } catch (e: Exception) {
        println("处理失败，回滚数据")
        e.printStackTrace()
        connector.rollback(batchId)
    } finally {
        connector.disconnect()
    }
}

fun printEntry(entrys: List<CanalEntry.Entry>) {
    entrys.forEach {
        if (it.entryType == CanalEntry.EntryType.TRANSACTIONBEGIN || it.entryType == CanalEntry.EntryType.TRANSACTIONEND) {
            return@forEach
        }
        var rowChange:CanalEntry.RowChange?=null
        try{
            rowChange= CanalEntry.RowChange.parseFrom(it.storeValue)

        }catch (e:Exception){
            throw RuntimeException("ERROR## parser error:"+it.toString(),e)
        }
        val eventType=rowChange.eventType
        println(eventType)
        rowChange.rowDatasList.forEach {
            if(eventType==CanalEntry.EventType.DELETE){
                printColumn(it.beforeColumnsList)
            }else if(eventType==CanalEntry.EventType.INSERT){
                printColumn(it.afterColumnsList)
            }else{
                println("*********>before")
                printColumn(it.beforeColumnsList)
                println("**********>after")
                printColumn(it.afterColumnsList)
            }
        }
    }
}

fun printColumn(columns:List<CanalEntry.Column>){
    columns.forEach {
        println("column:"+it.name+";"+it.value+";update="+it.updated)
    }
}