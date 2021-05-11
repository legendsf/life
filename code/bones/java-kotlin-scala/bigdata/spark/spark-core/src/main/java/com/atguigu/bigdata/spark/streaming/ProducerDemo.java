package com.atguigu.bigdata.spark.streaming;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class ProducerDemo {
 
	public final static String TOPIC="JAVA_TOPIC";
	public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String, String>(properties);
            Thread.sleep(1000);
            for (int i = 0; i < 100; i++) {
                String msg = "Message 发送卡夫卡消息-------" + i;
                producer.send(new ProducerRecord<String, String>(TOPIC,i+"", msg),  new Callback(){
					@Override
					public void onCompletion(RecordMetadata metadata, Exception exception) {
						
					}});
                    System.out.println("Sent:" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
 
    }
}