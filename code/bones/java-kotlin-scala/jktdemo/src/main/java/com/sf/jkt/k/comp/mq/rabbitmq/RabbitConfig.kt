package com.sf.jkt.k.comp.mq.rabbitmq

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.AsyncRabbitTemplate
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
class RabbitConfig {
    lateinit var host: String
    lateinit var port: String
    lateinit var username: String
    lateinit var password: String
    var publisherConfirms = false
    //    @Value("\${spring.rabbitmq.virtual-host}")
    lateinit var virtualHost: String

    companion object {
        val BOOT_TEST_EXCHANGE_NAME = "sf.service.message.exchange.direct.bootTest"
        val BOOT_TEST_QUEUE_NAME = "sf.service.message.queue.bootTest"
        val BOOT_TEST_ROUTING_KEY = "sf.service.message.routingkey.bootTest"
        const val BOOT_TEST_QUEUE_NAME_2 = "sf.service.message.queue.bootTest.2"
    }

    //创建工厂连接
    @Bean
    public fun cachingConnectionFactory(): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory()
        connectionFactory.setAddresses(this.host)
        connectionFactory.username = this.username
        connectionFactory.setPassword(this.password)
        connectionFactory.virtualHost = this.virtualHost
        connectionFactory.isPublisherConfirms = this.publisherConfirms
//        connectionFactory.setConnectionTimeout()
//        connectionFactory.closeTimeout
        return connectionFactory
    }


    @Bean
    //因为有回调确认所以必须是原型模式，否则就只有最后一个回调起作用
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun rabbitTemplate(): RabbitTemplate {
        val template= RabbitTemplate(this.cachingConnectionFactory())
        template.messageConverter=RabbitMqFastJsonConverter()
        return template
    }

    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun asyncRabbitTemplate():AsyncRabbitTemplate{
        return AsyncRabbitTemplate(rabbitTemplate())
    }

    /**
     * 可以不配置 rabbitAdmin 和 amqpAdmin ,会自动创建这里的@Bean 的 queue 和 exchange 以及 binding
     */
//    @Bean
//    fun rabbitAdmin(@Qualifier("cachingConnectionFactory") connectionFactory: ConnectionFactory): RabbitAdmin {
//        return RabbitAdmin(connectionFactory)
//    }

    @Bean
    fun booTestExchange(): DirectExchange {
        return DirectExchange(BOOT_TEST_EXCHANGE_NAME)
    }

    //会自动调用amqpAdmin 进行declare queue
    @Bean
    fun bootTestQueue(): Queue {
        return QueueBuilder.durable(BOOT_TEST_QUEUE_NAME).build()
    }

    @Bean
    fun booTestBinding(): Binding {
        return BindingBuilder.bind(bootTestQueue()).to(booTestExchange()).with(BOOT_TEST_ROUTING_KEY)
    }

    @Bean
    fun bootTestExchange2(): DirectExchange {
        return DirectExchange(BOOT_TEST_EXCHANGE_NAME)
    }

    @Bean
    fun bootTestQueue2(): Queue {
        return QueueBuilder.durable(BOOT_TEST_QUEUE_NAME_2).build()
    }

    //不同的QUEUE 绑定到同一个exchange 使用同一个routingkey，那么该份消息会收到两份
    @Bean
    fun bootTestBinding2(): Binding {
        return BindingBuilder.bind(bootTestQueue2()).to(bootTestExchange2()).with(BOOT_TEST_ROUTING_KEY)
    }

    @Bean
    fun fanoutQueueA():Queue{
        return QueueBuilder.durable("fanout.a").build()
    }
    @Bean
    fun fanoutQueueB():Queue{
        return QueueBuilder.durable("fanout.b").build()
    }

    @Bean
    fun fanoutExchange():FanoutExchange{
        return FanoutExchange("fanoutExchange")
    }
    @Bean
    fun fanoutBindingAtoExchange():Binding{
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange())
    }
    @Bean
    fun fanoutBindiingBtoExchange():Binding{
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange())
    }

    @Bean
    fun deadQueue():Queue{
        return QueueBuilder.durable("dead_queue").build()
    }

    @Bean
    fun deadExchange():DirectExchange{
        return DirectExchange("dead_exchange")
    }



    @Bean
    fun bingdingDeadExchange():Binding{
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("dead_routing_key")
    }

}