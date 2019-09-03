package com.sf.jkt.k.util


import cn.hutool.core.io.resource.ClassPathResource
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.Durations
import org.apache.spark.streaming.api.java.JavaStreamingContext
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object ScalaTest {
  def main(args: Array[String]): Unit = {
    test2()


  }

  def test2(): Unit = {
    var a = None
    var b = Some("b")
    println(b)
    println(a)
  }

  def test1(): Unit = {
    println("hello")

    val userdata = new ClassPathResource("movie/user.data")
    println(userdata.getAbsolutePath)
    println(userdata.getPath)

    var list = List(1, 2, 3)
    println(4 :: list)
    println(list)
    println(list.::(6))
    println(list)

    println("A" +: "B")
    println("A" :+ "B")
    var arr1 = ArrayBuffer("a", "b")
    var arr2 = ArrayBuffer("c", "d")
    arr1.++=:(arr2)
    println("-" * 10 + arr1)
    arr1.++=(arr2)
    println(arr1)


    println(arr1 +: arr2)
    println(arr1 :+ arr2)

    println(arr1 ++: arr2)
    println(arr1 ++ arr2)

    arr1 ++:= arr2
    println(arr1)
    println(arr2)
  }

  def testSpark() {

    //    val conf = new SparkConf().setMaster("local[2]").setAppName("demo")
    //    val context = StreamingContext(conf, Durations.seconds(8))
    //    val kafkaParams=new mutable.HashMap[String,Object]()
    //    kafkaParams.put("bootstrap.servers","127.0.0.1:9092")
    //    kafkaParams.put("key.deserializer",StringDeserializer)
    //    kafkaParams.put("value.deserializer",StringDeserializer)
    //    kafkaParams.put("group.id","mspark")
    //    kafkaParams.put("auto.offset.reset","latest")
    //    kafkaParams.put("enable.auto.commit",false)
    //    val topics=List("mspark")
  }


}



