package com.sf.jkt.k.biz.bigdata.spark.scala.movie.yore.sql

import java.util.Properties

import cn.hutool.core.io.resource.ClassPathResource
import org.apache.spark.SparkConf
import org.apache.spark.api.java.StorageLevels
import org.apache.spark.sql.{Row, SQLContext, SaveMode, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.storage.StorageLevel

object RddDfDsDemo {

  case class Person1(id: String, age: String, name: String)

  case class Person2(name: String, age: Int)

  case class Movie(id: String, name: String)

  case class Rating(uid: String, mid: String, rating: Int)

  def main(args: Array[String]): Unit = {
    testDsDfFilter()
  }

  def testDsDfFilter(): Unit = {
    val spark = SparkSession.builder().appName("testDsDfFilter").master("local[3]").getOrCreate()
    import spark.implicits._
    val sc = spark.sparkContext
    val sql = spark.sqlContext
    val classLoader = this.getClass.getClassLoader
    val movieRdd = sc.textFile(classLoader.getResource("movie/movies.data").getPath).map(_.split("\\|")).cache()
    val userRdd = sc.textFile(classLoader.getResource("movie/user.data").getPath).map(_.split("\\|")).cache()
    val ratingRdd = sc.textFile(classLoader.getResource("movie/ratings.data").getPath).map(_.split("\t")).cache()
    //需求女性最欢迎top10,用dataFrame join 实现
    //use dynamic structType
    val structType = StructType("uid,sex,age".split(",").map(field => StructField(field, StringType, nullable = true)))
    val userRow = userRdd.map(attr => Row(attr(0), attr(2),attr(1)))
    val userDf = spark.createDataFrame(userRow, structType).select("uid","sex").toDF()
    userDf.show(5)
    println("-userDf" * 10)
    val movieDf = movieRdd.map(attr => Movie(attr(0), attr(1))).toDF()
    movieDf.show(5)
    println("-movieDf" * 10)
    val ratingDf = ratingRdd.map(attr => Rating(attr(0), attr(1), attr(2).toInt)).toDF()
    ratingDf.show(5)
    println("-ratingDf" * 10)
    val maleDf = userDf.filter(userDf.col("sex").equalTo("M"))
    maleDf.show(5)
    println("-maleDf" * 10)
    val maleRating = userDf.filter(userDf.col("sex").equalTo("M")).join(ratingDf, "uid").rdd
    //user rdd map reduce
    //count avg
    var maleAvgRating = maleRating.map(x => (x(2).toString, (x(3).toString.toDouble, 1))).reduceByKey((x, y) => (x._1 + y._1, x
      ._2 + y._2)).map(x => (x._1, x._2._1 / x._2._2))
    maleAvgRating.toDF().show(5)
    println("-maleAndAvgRating"*10)
    val ratingAndMovie= maleAvgRating.toDF("id", "avgRating").join(movieDf, "id")
      ratingAndMovie.show(5)
    println("-ratingAndMovie"*10)
      ratingAndMovie.select("avgRating", "name")
      .show(5)
    println("-avgMaleRating" * 10)

    //USE spqrk.sql select

    ratingDf.createTempView("ratings")
    val userDf2=spark.createDataFrame(userRow,structType)
    userDf2.createTempView("users")

    val sqlstr="select sex,age,count(*) form users u join ratings r on u.uid=r.uid where mid=1081 group by sex,age"
    spark.sql(sqlstr).show(5)
    println("-sqlstr"*10)

    ratingDf.select("mid","rating")
        .groupBy("mid")
        .avg("rating")
        .orderBy($"avg(rating)".desc)
        .show(5)
    println("-dataFrameSuper"*10)

    ratingDf.as[Rating].select("mid","rating")
      .groupBy("mid")
      .avg("rating")
      .orderBy($"avg(rating)".desc)
      .show(5)
    println("-dataSetSuper"*10)

    spark.stop()

  }

  def testRedis(): Unit = {
    val spark = SparkSession.builder().appName("testRedis").master("local[2]")
      .config("spark.redis.host", "127.0.0.1")
      .config("spark.redis.port", "6379")
      //      .config("spark.redis.auth","")
      .config("spark.redis.db", "0")
      .getOrCreate()
    val person = Seq(Person2("sf", 18), Person2("gd", 18))
    val df = spark.createDataFrame(person)
    df.write.format("org.apache.spark.sql.redis")
      .option("table", "person2")
      .option("key.column", "name")
      .mode(SaveMode.Overwrite)
      .save()

    val loadedDf = spark.read.format("org.apache.spark.sql.redis")
      .option("table", "person2")
      .option("key.column", "name")
      .load()

    loadedDf.show(false)

    var person3 = spark.sqlContext.read.format("json").load("jktbigdata/src/main/resources/movie/person.json")
    person3.show()

    spark.stop()
  }


  def testReadAndWrite(): Unit = {
    val spark = SparkSession.builder().appName("testReadAndWrite").master("local[2]").getOrCreate()
    val sc = spark.sparkContext
    val sql = spark.sqlContext
    val mysqluri = "jdbc:mysql://localhost:3306/test?user=root&password=root"

    //    var result1=sql.read.format("jdbc").options(Map(
    //      "url"->mysqluri,
    //      "driver"->"com.mysql.jdbc.Driver",
    //      "dbtable"->"user"
    //    )).load().cache()

    var result1 = sql.read.options(Map("a" -> "b")).jdbc(mysqluri, "user", new Properties())

    result1.show()
    result1.select("name").limit(5).show()
    result1.createTempView("user")
    val result2 = spark.sql("select name,sex from user limit 5")
    result2.show()
    result2.write.mode(SaveMode.Append).jdbc("jdbc:mysql://localhost:3306/test?user=root&password=root", "user", new
        Properties())

    var person3 = spark.sqlContext.read.format("json").load("jktbigdata/src/main/resources/movie/person.json")
    person3.show()

    spark.stop()
  }

  def testDs2Df(): Unit = {
    val spark = SparkSession.builder().master("local[2]").appName("testDs2Df").getOrCreate()
    import spark.implicits._
    val sc = spark.sparkContext
    val personRdd = sc.textFile(new ClassPathResource("movie/person.txt").getAbsolutePath)
    val columns = "id,age,name".split(",").map(field => StructField(field, StringType, nullable = true))
    val structType = StructType(columns)
    val rowRdd = personRdd.map(_.split(",")).map(attr => Row(attr(0), attr(1), attr(2)))
    val personDf = spark.createDataFrame(rowRdd, structType)
    personDf.persist(StorageLevel.MEMORY_ONLY_SER)
    personDf.printSchema()
    personDf.createTempView("person")
    spark.sql("show tables").show()
    spark.sql("select * from person").show()

    val personDS = personDf.as[Person1]
    personDS.show()
    personDS.printSchema()
    personDS.select("name").show()
    personDS.createTempView("person1")
    spark.sql("show tables").show()
    val result = spark.sql("select * from person1")
    spark.sql("select * from person1 where 1=1 ").write.json("jktbigdata/src/main/resources/movie/person.json")


    spark.stop()
  }

  def testRdd_dynamic(): Unit = {
    val spark = SparkSession.builder().appName("RDD_dynamic").master("local[2]").getOrCreate()
    val sc = spark.sparkContext
    val personRdd = sc.textFile(new ClassPathResource("movie/person.txt").getAbsolutePath)
    val schema = "id,age,name"
    val fields = schema.split(",").map(field => StructField(field, StringType, nullable = true))
    val schemaType = StructType(fields)
    val rowRdd = personRdd.map(x => x.split(",")).map(attr => Row(attr(0), attr(1), attr(2)))
    val personDf = spark.createDataFrame(rowRdd, schemaType)
    personDf.printSchema()
    personDf.show()
    personDf.select("name").show()
    personDf.filter(personDf.col("id") > 2).show()

    personDf.createTempView("person")
    spark.sql("show tables")
    spark.sql("select * from person").show()

  }

  def testRdd_static(): Unit = {
    val spark = SparkSession.builder().appName("testRdd").master("local[2]").getOrCreate()
    val sc = spark.sparkContext
    val personRdd = sc.textFile(new ClassPathResource("movie/person.txt").getAbsolutePath)
      .map(_.split(",")).map(attr => Person(attr(0).trim.toInt, attr(1).trim.toInt, attr(2)))
    personRdd.take(2).foreach(println)
    import spark.implicits._
    val personDF = personRdd.toDF()
    //    personDF.cache()
    personDF.persist(StorageLevel.MEMORY_ONLY_SER)
    personDF.show()

    personDF.select("name").show()
    personDF.filter(personDF.col("id") > 2).show()
    println("---------------------------")

    personDF.createTempView("person")
    spark.sql("show tables").show()
    spark.sql("select * from person").show()


    spark.stop()

  }
}

case class Person(id: Int, age: Int, name: String)
