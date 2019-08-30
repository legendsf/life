package com.sf.jkt.k.biz.bigdata.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object DirectKafka {

  val checkPointDir = "/checkPointDir"

  def createContext(checkPoint: Boolean = false): StreamingContext = {
    val conf = new SparkConf().setAppName("directKafka").setMaster("local[2]")
    var ssc = new StreamingContext(conf, Seconds(2))
    if (checkPoint) {
      ssc.checkpoint(checkPointDir)
    }
    return ssc
  }

  def rddCheckPoint(ssc: StreamingContext): Unit = {
    //    val batchDuration=1000
    //    val rdds=ssc.socketTextStream()

    //    rdds.checkpoint(Seconds(batchDuration*5))
    // 有了上面定时的不需要现在每次的保存
    //    rdds.foreachRDD(rdd=>{
    //      rdd.checkpoint()
    //    })

  }

  def main(args: Array[String]): Unit = {
    //如果首次启动，创建一个新的StreamContext
    //若application 是从失败中重启，将会从checkPointDir目录中导入checkPoint数据来创建新的streamContext 实例
    //    val ssc=StreamingContext.getOrCreate(checkPointDir,createContext)
    val ssc = createContext()
    rddCheckPoint(ssc)
    val topicList = List("mspark")
    val params = collection.mutable.HashMap[String,String]()
    params.put("bootstrap.servers", "127.0.0.1:9092")
    params.put("group.id", "mspark")
    params.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    params.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    val message = KafkaUtils.createDirectStream[String, String](ssc, LocationStrategies.PreferConsistent, ConsumerStrategies
      .Subscribe[String, String]
      (topicList, params))
    message.map(it => println("HHHHHHHHHHH" + it.key() + "|" + it.value()))
    val lines = message.map(_.value)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()
  }

}
