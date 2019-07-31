package com.sf.jkt.k.comp.mq.rabbitmq

import com.sf.jkt.k.entity.User
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@DependsOn("rabbitReciver")
@Component
@RabbitListener(containerFactory = "rabbitListenerContainerFactory2",
        bindings = [org.springframework.amqp.rabbit.annotation.QueueBinding(value = Queue(value = "bootTest6", durable = "true"),
                exchange = Exchange(value = "bootTest6", durable = "true"), key = ["bootTest6"])])
class MessageHandler {
    private val log= LoggerFactory.getLogger(this.javaClass)
    @RabbitHandler
    fun handleMessage6(user: User) {
        log.info("handleMessage6.Msg:" + user)
    }
}