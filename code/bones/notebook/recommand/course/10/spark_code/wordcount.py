import sys
 
from pyspark import SparkContext, SparkConf
 
if __name__ == "__main__":
 
  # create Spark context with Spark configuration
  conf = SparkConf().setAppName("Word Count - Python").set("spark.hadoop.yarn.resourcemanager.address", "192.168.0.104:8032")
  sc = SparkContext(conf=conf)
 
  # read in text file and split each document into words
  words = sc.textFile("./input/wc.txt").flatMap(lambda line: line.split(" "))
 
  # count the occurrence of each word
  wordCounts = words.map(lambda word: (word, 1)).reduceByKey(lambda a,b:a+b)
 
  wordCounts.saveAsTextFile("/Users/maxiao/Documents/workspace4py/spark_test/output/")
