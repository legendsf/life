package com.sf.jkt.k.comp.mq.kafka

import com.sf.jkt.k.util.SpringBootBaseTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import java.util.concurrent.TimeUnit

class BootKafkaTest :SpringBootBaseTest(){
    @Autowired
    lateinit var kafkaProducer: KafkaProducer
    lateinit var kafkaTemplate: KafkaTemplate<String,String>

    @Test
    fun test_sendMsg(){
        kafkaProducer.sendMessage("SIMPLE","hello SIMPLE MESSAGE")
    }

    @Test
    fun test_ackTest(){
       for(i in 0..10){
           kafkaProducer.sendMessage("ack_test","ack_test_message#"+i)
       }
        TimeUnit.SECONDS.sleep(100)
    }
   @Test
   fun test_ackTestNoAck(){
       //消费者重新启动后包含#1的message 会再发过来一次
//       kafkaProducer.sendMessage("ack_test","ack_test_message#1")
//       TimeUnit.SECONDS.sleep(100)
   }
}
