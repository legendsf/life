package com.sf.jkt.k.comp.mq.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

@Component
public class RabbitAutoBinding {

    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "bootTestJava", durable = "true"),
            exchange = @Exchange(value = "bootTestJava", durable = "true"), key = "bootTestJava")})
    public void handleMessage(Message message) {
        System.out.println("====消费消息" + message.getMessageProperties().getConsumerQueue() + "===handleMessage");
        System.out.println(message.getMessageProperties());
        System.out.println(new String(message.getBody()));
    }

}
