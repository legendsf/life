package com.atguigu.bigdata.spark.streaming;

import org.apache.commons.io.FileUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.spark.internal.config.R;

import java.io.File;
import java.util.List;
import java.util.Properties;

public class CustomProducerTest {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        //kafka 集群，broker-list
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("acks", "0");
        //重试次数
        props.put("retries", 1);
        //批次大小
        props.put("batch.size", 16384);
        //等待时间
        props.put("linger.ms", 1);
        //RecordAccumulator 缓冲区大小
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        List<String> listJson = FileUtils.readLines(new File("data/sample2.txt"),"UTF-8");

        try {
            for(String json:listJson) {
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic_gong", json);
                producer.send(record);
                System.out.println("消息发送成功:" + json);
                Thread.sleep(100);
            }
                
        } finally {
            producer.close();
        }
    }

}