package com.sf.jkt.k.comp.mq.rabbitmq

import com.google.gson.Gson
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.connection.CorrelationData
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class RabbitSender :RabbitTemplate.ConfirmCallback {
    private val log= LoggerFactory.getLogger(this.javaClass)
    @Autowired
    lateinit var rabbitTemplate: RabbitTemplate
    fun sendMsg(any:Any){
        val message= Gson().toJson(any)
        val correlationId=CorrelationData(UUID.randomUUID().toString())
        rabbitTemplate.setConfirmCallback(this)
        rabbitTemplate.convertAndSend(RabbitConfig.BOOT_TEST_EXCHANGE_NAME,RabbitConfig.BOOT_TEST_ROUTING_KEY,
                message,correlationId)
    }
    override fun confirm(correlationData: CorrelationData?, ack: Boolean, cause: String?) {
        log.info("回调ID:"+correlationData)
        if(ack){
            log.info("消息消费成功")
        }else{
            log.info("消息消费失败")
            //重发
        }
    }
}