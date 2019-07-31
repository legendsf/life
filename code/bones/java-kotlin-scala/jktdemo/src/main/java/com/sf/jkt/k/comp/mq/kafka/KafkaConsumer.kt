package com.sf.jkt.k.comp.mq.kafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.AcknowledgeMode
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.KafkaListeners
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component
//https://shift-alt-ctrl.iteye.com/blog/2423167
@Component
class KafkaConsumer {
    private val log = LoggerFactory.getLogger(this.javaClass)
    @KafkaListener(topics = ["ORDER"], groupId = "OrderGroup")
    fun listenToOrder(data: String) {
        log.info("收到订单消息:" + data)
    }

    @KafkaListener(topics = ["MEMBER"], groupId = "MemberGroup")
    fun listenToMember(data: String) {
        log.info("收到会员消息:" + data)
    }

    @KafkaListener(topics = ["SIMPLE"], groupId = "SIMPLE")
    fun listenToSIMPLE(data: String) {
        log.info("收到SIMPLE消息:" + data)
    }

    @KafkaListener(topics = ["ack_test"], groupId = "ack_test")
    fun listenToAckTest(data: String, ack: Acknowledgment) {
        log.info("收到ackTest:" + data)
        if (data.contains("#1")) {
            log.info("message包含#1,不ack")
        } else {
            ack.acknowledge()
        }
    }

//    @KafkaListener(containerFactory = "kafkaListenerContainerFactory",topics = ["ack_test"], groupId = "ack_test")
//    fun listenToAckTest(records:List<ConsumerRecord<String, String>>, ack:Acknowledgment) {
//        log.info("收到AckTest"+records)
//        records.forEach {record->
//            log.info("收到AckTest:"+record.value())
//            if(!record.value().contains("#1")){
//                ack.acknowledge()
//            }
//        }
//    }


//    @KafkaListeners(
//            KafkaListener(topics = ["ORDER"], groupId = "OrderGroup"),
//            KafkaListener(topics = ["MEMBER"], groupId = "MemberGroup")
//    )
//    fun listenToAll(data: String) {
//        log.info("收到订单或者会员消息:" + data)
//    }
}