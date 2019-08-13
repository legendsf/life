package com.sf.jkt.k.comp.mq.rabbitmq

import com.rabbitmq.client.Channel
import org.omg.CORBA.Object
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.AcknowledgeMode
import org.springframework.amqp.core.ExchangeTypes
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.*
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener
import org.springframework.amqp.support.AmqpHeaders
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import java.util.concurrent.atomic.AtomicInteger


@DependsOn("rabbitConfig")
@Configuration
class RabbitReciver {
    private val log = LoggerFactory.getLogger(this.javaClass)
    @Autowired
    lateinit var rabbitConfig: RabbitConfig

    companion object{
        val ai=AtomicInteger();
    }

    @Bean
    fun rabbitListenerContainerFactory(configure: SimpleRabbitListenerContainerFactoryConfigurer,
                                       @Qualifier("cachingConnectionFactory") connectionFactory: ConnectionFactory)
            : SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConcurrentConsumers(5)
//        factory.setPrefetchCount(10)
//        //consuerm ack 机制
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL)
        configure.configure(factory, connectionFactory)
        return factory
    }

    @Bean
    fun rabbitListenerContainerFactory2(@Qualifier("cachingConnectionFactory") connectionFactory: ConnectionFactory)
            : SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory)
        factory.setMaxConcurrentConsumers(10)
        factory.setConcurrentConsumers(5)
        factory.setMessageConverter(RabbitMqFastJsonConverter())
//        factory.setDefaultRequeueRejected(false)
        return factory
    }

    @Bean
    fun rabbitListenerContainerFactory3(@Qualifier("cachingConnectionFactory") connectionFactory: ConnectionFactory)
            : SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory)
        factory.setMaxConcurrentConsumers(10)
        factory.setConcurrentConsumers(2)
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL)
        factory.setPrefetchCount(2)
        factory.setMessageConverter(RabbitMqFastJsonConverter())
//        factory.setDefaultRequeueRejected(false)
        return factory
    }

    @Bean
    fun messageContainer(): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer(rabbitConfig.cachingConnectionFactory())
        container.setQueues(rabbitConfig.bootTestQueue())
        container.isExposeListenerChannel = true
        container.setMaxConcurrentConsumers(1)
        container.setConcurrentConsumers(1)
        container.acknowledgeMode = AcknowledgeMode.MANUAL
        container.setMessageListener(object : ChannelAwareMessageListener {
            override fun onMessage(message: Message, channel: Channel) {
                val body = message.body
                log.info("messageContainer.receive msg: " + String(body))
                channel.basicAck(message.messageProperties.deliveryTag, false)
            }
        })
        return container
    }

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", queues = [RabbitConfig.BOOT_TEST_QUEUE_NAME_2])
    fun handleBootTestMessage(message: String) {
        log.info("handleBootTestMessage.receive message:" + message)
    }

    //同一个queue的消息只会被消费一次
    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", queues = [RabbitConfig.BOOT_TEST_QUEUE_NAME_2])
    fun handleBootTestMessage2(message: Message) {
        log.info("handleBootTestMessage2.receive message:" + String(message.body) + "|messageProperies:" + message
                .messageProperties)
    }

    /**queues 是数组，一个监听器可以监听多个queue: ["q1","q2"]
     * 可以用yaml中的配置文件 ["\${sf.q1}","\${"sf.q2"}"]
     **/
    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", queues = [RabbitConfig.BOOT_TEST_QUEUE_NAME_2])
    fun handleBootTestMessage3(@Payload body: String, @Headers headers: Map<String, Object>) {
        log.info("handleBootTestMessage3.receive message:" + body + "|headers:" + headers)
    }

    /**
     * 自动生成queue exchange 并进行绑定
     * //支持自动声明绑定，声明之后自动监听队列的队列，此时@RabbitListener注解的queue和bindings不能同时指定，否则报错
     * for java :
    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = "q5",durable = "true"),
    exchange =@Exchange(value = "zhihao.miao.exchange",durable = "true"),key = "welcome")})
     */
    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", bindings = [QueueBinding(value = Queue(value = "bootTest4", durable = "true"),
            exchange = Exchange(value = "bootTest4", durable = "true"), key = ["bootTest4"])])
    fun handleBootTestMessage4(message: Message) {
        log.info("handleBootTestMessage4.receive message:" + String(message.body) + "|messageProperies:" + message
                .messageProperties)
    }

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory2", bindings = [QueueBinding(value = Queue
    (value = "bootTest5", durable = "true"),
            exchange = Exchange(value = "bootTest5", durable = "true"), key = ["bootTest5"])])
    fun handleBootTestMessage5(message: Message) {
        log.info("handleBootTestMessage5.receive message:" + String(message.body) + "|messageProperies:" + message
                .messageProperties)
    }

    @RabbitListener(queues = ["fanout.a"])
    fun handleFanoutMessageA(message: ByteArray) {
        log.info("handleFanoutMessageB.receive.msg:" + String(message))
    }

    @RabbitListener(queues = ["fanout.b"])
    fun handleFanoutMessageB(message: ByteArray) {
        log.info("handleFanoutMessageB.receive.msg:" + String(message))
    }

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory3", bindings = [QueueBinding(value = Queue(value
    = "topic.queueA", durable = "true"), exchange = Exchange(value = "topicExchange", durable = "true", type = ExchangeTypes
            .TOPIC), key = ["sftopicA.#"]),
        QueueBinding(value = Queue(value
        = "topic.queueB", durable = "true"), exchange = Exchange(value = "topicExchange", durable = "true", type =
        ExchangeTypes
                .TOPIC), key = ["topic.#"])])
    fun handleTopicMsg(@Payload body: String, @Headers headers: Map<String, Object>, @Header(AmqpHeaders.DELIVERY_TAG)
    deliveryTag: Long, channel: Channel) {
        log.info("handleTopicMsg.ack.message:" + body+"|deliveryTag:"+deliveryTag)
//        if(deliveryTag==10L){
//            TimeUnit.SECONDS.sleep(100)
//        }
//        if(deliveryTag !=10L){
//            channel.basicAck(deliveryTag, false)
//        }
        if(!body.contains("#1")){
            // update db statas=1
            channel.basicAck(deliveryTag, false)
        }
    }

    /**
     * 创建私信队列，并绑定
     */

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", bindings = [
//        QueueBinding(value = Queue(value
//    = "dead_queue", durable = "true"),
//            exchange = Exchange(value = "dead_exchange", durable = "true"), key = ["dead_routing_key"]),
        QueueBinding(value = Queue
        (value = "handleRpcMsg", durable = "true", arguments = [
//            Argument(name = "x-dead-letter-exchange", value = "dead_exchange"),
//            Argument(name = "x-dead-letter-routing-key", value = "dead_routing_key")
        ]), exchange = Exchange(value = "handleRpcMsg", durable = "true"), key = ["handleRpcMsg"])
    ])
    fun handleRpcMsg(message: String): String {
        log.info("handleRpcMsg.message:" + message)
        return "I recived!"
    }
}

