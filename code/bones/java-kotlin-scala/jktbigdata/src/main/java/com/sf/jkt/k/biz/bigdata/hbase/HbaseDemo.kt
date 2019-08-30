package com.sf.jkt.k.biz.bigdata.hbase

import org.apache.hadoop.hbase.*
import org.apache.hadoop.hbase.client.*
import org.apache.hadoop.hbase.filter.*
import org.apache.hadoop.hbase.util.Bytes
import java.util.concurrent.TimeUnit

fun main() {
    testHbase()
}

fun testHbase(){
    val config=HBaseConfiguration.create()
    config.set("hbase.zookeeper.quorum","127.0.0.1")
//    config.set("hbase.zookeeper.quorum","22c6047560b9")
//    config.set("hbase.zookeeper.property.clientPort","2181")
    val connection=ConnectionFactory.createConnection(config)
    val admin=connection.admin
    val tableName=TableName.valueOf("mytable")
    //delete
    if(admin.tableExists(tableName)){
        admin.deleteTable(tableName)
    }
    //create
    val tableDes=HTableDescriptor(tableName)
    val columnDes=HColumnDescriptor("cf")
    tableDes.addFamily(columnDes)
    admin.createTable(tableDes)
    println("创建表成功")
    //insert
    val table=connection.getTable(TableName.valueOf("mytable"))
    val al= mutableListOf<Put>()
    val put=Put(Bytes.toBytes("rowkey1"))
    put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("msg"),Bytes.toBytes("hello world"))
    TimeUnit.SECONDS.sleep(1)
    put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("msg"),Bytes.toBytes("hello world"))
    TimeUnit.SECONDS.sleep(1)
    put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("msg"),Bytes.toBytes("hello world"))
    al.add(put)
    table.put(al)
    //query by rowkey
    val get= Get(Bytes.toBytes("rowkey1"))
    get.setMaxVersions(3)
    val rs=table.get(get)
    for(cell in rs.rawCells()){
        println("key:"+String(CellUtil.cloneRow(cell))+"|列族为:"+String(CellUtil.cloneFamily(cell))+"|列标识符为:"
                +String(CellUtil.cloneQualifier(cell))+"|值为:"+String(CellUtil.cloneValue(cell)))
    }
    //scan all
    val scan=table.getScanner(Scan())
    //scan by range
    val scan1=table.getScanner(Scan(Bytes.toBytes("rowkey1"),Bytes.toBytes("rowkey1000")))
    //scan by filter
    val filters= mutableListOf<Filter>()
    //列值大于hello的数据
    val cfilter1=SingleColumnValueFilter(Bytes.toBytes("cf"),Bytes.toBytes("msg"), CompareFilter.CompareOp.GREATER,
            Bytes.toBytes("hello"))
    val cfilter2=SingleColumnValueFilter(Bytes.toBytes("cf"),Bytes.toBytes("msg"), CompareFilter.CompareOp.LESS,
            Bytes.toBytes("XSTREAM"))
    filters.add(cfilter1)
    filters.add(cfilter2)
    val filterList= FilterList(FilterList.Operator.MUST_PASS_ALL,filters)
    val sfilter=Scan()
    sfilter.setFilter(filterList)
    val resultFilter=table.getScanner(sfilter)
    //scan by row
    // rowkey 中包含 row 的数据
    val filter2=RowFilter(CompareFilter.CompareOp.EQUAL,SubstringComparator("row"))
    sfilter.setFilter(FilterList(FilterList.Operator.MUST_PASS_ONE,filter2))
    val result2=table.getScanner(sfilter)
    //scan by regex

    //close
    admin.close()
    connection.close()


}
