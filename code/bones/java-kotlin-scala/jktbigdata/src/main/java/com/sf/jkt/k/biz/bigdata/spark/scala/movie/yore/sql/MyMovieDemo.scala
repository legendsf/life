package com.sf.jkt.k.biz.bigdata.spark.scala.movie.yore.sql

import cn.hutool.core.io.resource.ClassPathResource
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object MyMovieDemo {
  def main(args: Array[String]): Unit = {
    //    movieAnalyzer1()
    //    join()
    movieFilter1()
  }

  def join(): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("join")
    val sc = new SparkContext(conf)
    val rdd1 = sc.makeRDD(Array(("1", "spark"), ("2", "hadoop"), ("3", "scala"), ("4", "java")), 2)
    val rdd2 = sc.makeRDD(Array(("1", "30K"), ("2", "40k"), ("3", "50k"), ("4", "60k"), ("5", "70K")), 2)
    val joinRdd = rdd1.join(rdd2).collect().foreach(println)
    println("-join" * 10)
    val leftJoinRdd = rdd1.leftOuterJoin(rdd2).collect().foreach(println)
    println("-left" * 10)
    val rightJoinRdd = rdd1.rightOuterJoin(rdd2).collect().foreach(println)
    println("-right" * 10)
    sc.cancelAllJobs()
  }

  def movieAnalyzer1(): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("movie1")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val sc = spark.sparkContext
    sc.setLogLevel("warn")
    val userdata = new ClassPathResource("movie/user.data")
    val moviedata = new ClassPathResource("movie/movies.data")
    val ratingdata = new ClassPathResource("movie/ratings.data")

    val usersRDD = sc.textFile(userdata.getAbsolutePath)
    val moviesRDD = sc.textFile(moviedata.getAbsolutePath)
    val ratingsRDD = sc.textFile(ratingdata.getAbsolutePath)

    //(movieId,movieName)
    val movieInfo = moviesRDD.map(_.split("\\|")).map(x => (x(0), x(1))).cache()
    //(userId,movieId,rating)
    val ratings = ratingsRDD.map(_.split("\t")).map(x => (x(0), x(1), x(2))).cache()
    //(movieId,(sum(rating),count))
    val movieAndRatings = ratings.map(x => (x._2, (x._3.toDouble, 1))).reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))
    //(movieId,avgRating)
    val avgRating = movieAndRatings.map(x => (x._1, x._2._1.toDouble / x._2._2))
    //(movieId,(avgRating,movieName))
    val ratingAndInfo = avgRating.join(movieInfo)
    //(movieName,avgRating)
    val nameRating = ratingAndInfo.map(x => (x._2._2, x._2._1))
    nameRating.sortByKey(false).take(12).foreach(record => printf("%s评分为%.1f\n", record._1, record._2))
    spark.stop()
  }

  def movieFilter1(): Unit = {
    val conf = new SparkConf().setAppName("movieFilter1").setMaster("local[2]")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val sc = spark.sparkContext
    val userRDD = sc.textFile(new ClassPathResource("movie/user.data").getAbsolutePath)
    val moviesRDD = sc.textFile(new ClassPathResource("movie/movies.data").getAbsolutePath)
    val ratingsRDD = sc.textFile(new ClassPathResource("movie/ratings.data").getAbsolutePath)
    //want (uid,(uid,mid,avgRating),Sex)
    val movieInfo = moviesRDD.map(_.split("\\|")).map(x => (x(0), x(1))).cache()
    //(uid,usex)
    val userInfo = userRDD.map(_.split("\\|")).map(x => (x(0), x(2))).cache()
    //(uid,mid,rating)
    val ratings = ratingsRDD.map(_.split("\t")).map(x => (x(0), x(1), x(2))).cache()
    //rating join user (uid,(uid,mid,rating),sex)
    val ratingAndUser = ratings.map(x => (x._1, (x._1, x._2, x._3))).join(userInfo).cache()
    //filter F/M
    val female = ratingAndUser.filter(x => x._2._2.equalsIgnoreCase("F")).map(x => x._2._1)
    val male = ratingAndUser.filter(x => x._2._2.equalsIgnoreCase("M")).map(x => x._2._1)
    println("女性最喜欢看的top10")
    //(rating,movieName)
    female.map(x => (x._2, (x._3.toDouble, 1))).reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2)).map(x => (x._1, x._2._1.toDouble / x
      ._2._2)).join(movieInfo).map(x => (x._2._1, x._2._2)).sortByKey(false).take(10).foreach(record =>
      printf("%s的评分为:%.1f\n", record._2, record._1))

    ratingsRDD.map(line=>{
      val splited=line.split("\t")
      (new MySecondKey(splited(3).toInt,splited(2).toInt),line)
    }).sortByKey(false).map(sortedLine=>sortedLine._2).take(10).foreach(println)
    println("-sorted"*10)

    spark.stop()

  }

}

class MySecondKey(val first: Double, val second: Double) extends Ordered[MySecondKey] with Serializable {
  override def compare(that: MySecondKey): Int = {
    if (this.first - that.first != 0) {
      (this.first - that.first).toInt
    } else {
      if (this.second - that.second > 0) {
        //上取整
        Math.ceil(this.second - that.second).toInt
      } else if (this.second - that.second < 0) {
        Math.floor(this.second - that.second).toInt
      } else {
        (this.second - that.second).toInt
      }
    }
  }
}