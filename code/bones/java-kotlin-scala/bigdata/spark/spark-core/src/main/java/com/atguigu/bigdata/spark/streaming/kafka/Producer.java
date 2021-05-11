package com.atguigu.bigdata.spark.streaming.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Producer {
    public static void main(String[] args)throws Exception {
        //配置信息
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", "127.0.0.1:9092");
        //设置数据key和value的序列化处理类
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);
        //创建生产者实例
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);
        ProducerRecord record = new ProducerRecord<String, String>("topic1", "userName", "lc");
        //发送记录
        while (true){
            System.out.println("发送消息："+record);
            producer.send(record);
            TimeUnit.MILLISECONDS.sleep(100);
        }
//        producer.close();
    }
}