package com.sf.jkt.k.comp.mq.rabbitmq

import org.springframework.amqp.rabbit.AsyncRabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AsyncSender {
   @Autowired
   lateinit var asyncRabbitTemplate: AsyncRabbitTemplate
    fun send(){

    }
}