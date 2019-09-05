package com.sf.jkt.k.biz.bigdata.spark.scala.study

import org.apache.spark.sql.SparkSession

import scala.collection.mutable.ListBuffer

object SparkStudyDemo {
  def main(args: Array[String]): Unit = {
    test_mapPartitions()
  }

  def test_glom(): Unit = {
    val rowsList = List[List[Double]](
      List(50.0, 40.0, 44.0),
      List(88, 44.0, 44.0),
      List(855, 0, 55.0, 44.0),
      List(855, 0, 55.0, 70.0)
    )
    val weights = List(1.0, 0.5, 3)
    val sc=getSpark().sparkContext
    val rowRDD = sc.makeRDD(rowsList)

    rowRDD.glom().collect()
    sc.stop()

  }
  def getSpark(): SparkSession ={
    val spark=SparkSession.builder().appName("getspark").master("local[2]").getOrCreate()
    return spark
  }

  def test_mapPartitions(): Unit = {
    val spark = SparkSession.builder().master("local[2]").appName("mapPartition").getOrCreate()
    val sc = spark.sparkContext

    val students = new ListBuffer[String]()
    for (i <- 1 to 100) {
      students += "test:" + i
    }
    val stus = sc.parallelize(students, 4)

    stus.fold("a")(_+_).foreach(println)

    /*
    错误示例:每个元素执行 获取连接释放连接
    */
    //    stus.foreach(x => {
    //      val connection =  DBUtils.getConnection()
    //      println(connection + "~~~~~~~~~~~~")
    //      // TODO... 写出数据到数据库
    //
    //      DBUtils.returnConnection(connection)
    //    })

    stus.foreachPartition(
      partion => partion
      //    val connection =  DBUtils.getConnection()
      //    println(connection + "~~~~~~~~~~~~")
      //    // TODO... 写出数据到数据库
      //
      //    DBUtils.returnConnection(connection)
    )

    stus.mapPartitions(
      partion => partion
      //    val connection =  DBUtils.getConnection()
      //    println(connection + "~~~~~~~~~~~~")
      //    // TODO... 写出数据到数据库
      //
      //    DBUtils.returnConnection(connection)
    ).foreach(println)

    //重新分区
    stus.repartition(5).mapPartitionsWithIndex(
      (index, partition) => (partition)

    )

    sc.stop()

  }
}
