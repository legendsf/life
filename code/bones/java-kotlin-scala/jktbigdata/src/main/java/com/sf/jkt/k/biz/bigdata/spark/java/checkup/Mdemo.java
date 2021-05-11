package com.sf.jkt.k.biz.bigdata.spark.java.checkup;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;

/***
 * spark 进行对账
 * https://blog.csdn.net/iteye_13851/article/details/82644121
 */
public class Mdemo {
    public static void main(String[] args) {
       SparkConf conf= new SparkConf().setMaster("local[2]").setMaster("local[*]");
       SparkContext sc=new SparkContext(conf);
    }
}
