package com.sf.jkt.k.biz.bigdata.spark.scala.movie.yore.rdd

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  *
  * Created by yore on 2019/1/25 17:10
  */
object WordCountJobRuntime {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ALL)

    val conf = new SparkConf
    conf.setAppName("Wow, WordCountJobRuntime!")
    conf.setMaster("local[1]")

    val sc = new SparkContext(conf)

    val lines = sc.textFile("demo/business-practice/movie-rating/src/main/resources/helloSpark.txt")

    val words = lines.flatMap(line => line.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCountsOdered = pairs.reduceByKey(_+_)
      .saveAsTextFile("demo/business-practice/movie-rating/src/main/resources/helloSpark_result.log")

    while (true){

    }

    sc.stop()


  }

}
