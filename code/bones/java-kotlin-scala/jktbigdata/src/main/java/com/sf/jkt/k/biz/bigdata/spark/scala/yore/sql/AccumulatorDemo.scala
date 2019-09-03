package com.sf.jkt.k.biz.bigdata.spark.scala.yore.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable.ArrayBuffer

object AccumulatorDemo {
  def main(args: Array[String]): Unit = {
    val conf =new SparkConf().setMaster("local[2]").setAppName("AccumulatorDemo")
    val spark=SparkSession.builder().config(conf).getOrCreate()
    val sc=spark.sparkContext
    val accumulator1=sc.longAccumulator("accumulator1")
    sc.parallelize(Array(1,2,3,4,5,6)).foreach(x=>(accumulator1.add(x)))
    println("-"*10+accumulator1.value)

    val accu2=new MyAccumulator1()
    sc.register(accu2,"MyAccumulator1")
    sc.parallelize(Array("a","b","c","d")).foreach(x=>accu2.add(x))
    println("-"*10+accu2.value)

    spark.stop()
  }
}

class MyAccumulator1 extends AccumulatorV2[String,ArrayBuffer[String]]{
  private var result=ArrayBuffer[String]()


  override def isZero: Boolean = this.result.size==0

  override def copy(): AccumulatorV2[String, ArrayBuffer[String]] = {
    val newAccu=new MyAccumulator1
    newAccu.result=this.result
    newAccu
  }

  override def reset(): Unit =this.result= new ArrayBuffer[String]()

  override def add(v: String): Unit = this.result +=v

  override def merge(other: AccumulatorV2[String, ArrayBuffer[String]]): Unit = {
    this.result.++=:(other.value)
  }

  override def value: ArrayBuffer[String] = this.result
}