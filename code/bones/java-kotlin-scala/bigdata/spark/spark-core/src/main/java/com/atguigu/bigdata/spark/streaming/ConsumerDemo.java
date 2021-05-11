package com.atguigu.bigdata.spark.streaming;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemo {
 
	 public static void main(String[] args){
	        Properties properties = new Properties();
	        properties.put("bootstrap.servers", "127.0.0.1:9092");
	        properties.put("group.id", "group-1");
	        properties.put("enable.auto.commit", "true");
	        properties.put("auto.commit.interval.ms", "1000");
	        properties.put("auto.offset.reset", "earliest");
	        properties.put("session.timeout.ms", "6000");
		 properties.put("heartbeat.interval.ms", "2000");
		 properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        @SuppressWarnings("resource")
			KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
	        //kafkaConsumer.subscribe(Arrays.asList(ProducerDemo.TOPIC1,ProducerDemo.TOPIC2));
	        kafkaConsumer.subscribe(Arrays.asList("JAVA_TOPIC"));
	        while (true) {
//	        	try {
//					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
	            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(1L));
	            for (ConsumerRecord<String, String> record : records) {
	                System.out.printf("接受卡夫卡消息内容为——————————————》》offset = %d, value = %s", record.offset(), record.value());
	                System.out.println();
	            }
	        }
 
	    }
}