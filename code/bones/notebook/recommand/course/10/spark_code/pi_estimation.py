import sys
 
from pyspark import SparkContext, SparkConf
import random

def inside(p):
    x, y = random.random(), random.random()
    return x*x + y*y < 1
 
if __name__ == "__main__":
 
  # create Spark context with Spark configuration
  conf = SparkConf().setAppName("PI - Python").set("spark.hadoop.yarn.resourcemanager.address", "192.168.0.104:8032")
  sc = SparkContext(conf=conf)
  NUM_SAMPLES = 10000
  count = sc.parallelize(range(0, NUM_SAMPLES)) \
             .filter(inside).count()
  print("Pi is roughly %f" % (4.0 * count / NUM_SAMPLES))


