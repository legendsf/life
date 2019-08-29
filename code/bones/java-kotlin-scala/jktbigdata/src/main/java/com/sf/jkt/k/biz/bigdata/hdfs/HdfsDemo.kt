package com.sf.jkt.k.biz.bigdata.hdfs

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FSDataInputStream
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.IOUtils
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.URI

fun main() {
    testUpAndDown()
}

var hdfsUri = "hdfs://localhost:32790"

fun testUpAndDown() {
    val fileSystem = FileSystem.get(URI(hdfsUri), Configuration(), "root")
//    var ins1=FileInputStream("f:/tmp/bigdata/upload.txt")
//    var ous1=fileSystem.create(Path("/hello/upload.txt"))
//    IOUtils.copyBytes(ins1,ous1,4096,true)
    println("upload success")
    var ins=fileSystem.open(Path("/hello/test1.txt"))
    var ous=FileOutputStream("f:/tmp/bigdata/pokes.txt")
    IOUtils.copyBytes(ins,ous,4096,true)
    println("download success")
    fileSystem.close()
}