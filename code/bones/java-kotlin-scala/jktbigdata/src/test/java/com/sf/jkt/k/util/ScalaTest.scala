package com.sf.jkt.k.util


import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.Durations
import org.apache.spark.streaming.api.java.JavaStreamingContext
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

import scala.collection.mutable

object ScalaTest {
  def main(args: Array[String]): Unit = {
    println("hello")


  }
  def testSpark(){

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



