package com.sf.jkt.k.comp.mq.rabbitmq

import com.alibaba.fastjson.JSON
import com.sf.jkt.k.entity.User
import com.sf.jkt.k.util.SpringBootBaseTest
import org.junit.Test
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.AsyncRabbitTemplate
import org.springframework.amqp.rabbit.connection.CorrelationData
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.concurrent.ListenableFutureCallback
import java.util.*
import java.util.concurrent.TimeUnit

class BootRabbitmqTest : SpringBootBaseTest() {

    @Autowired
    lateinit var rabbitTemplate: RabbitTemplate
    @Autowired
    lateinit var rabbitSender: RabbitSender
    private val log= LoggerFactory.getLogger(this.javaClass)
    @Autowired
    lateinit var asyncRabbitTemplate: AsyncRabbitTemplate

    @Test
    fun test_sendMsg() {
        for (i in 0..200) {
            rabbitSender.sendMsg("hello good luck#" + i)
        }
    }

    @Test
    fun test_sendMsg2() {
        rabbitTemplate.convertAndSend("bootTest4", "bootTest4", "sendMsg2",
                CorrelationData(UUID.randomUUID().toString()))
    }

    @Test
    fun test_sendMsg5() {
        rabbitTemplate.convertAndSend("bootTest5", "bootTest5", User(1, "sf1", "pass1"),
                CorrelationData(UUID.randomUUID().toString()))
    }

    @Test
    fun test_sendMsg6() {
        rabbitTemplate.convertAndSend("bootTest6", "bootTest6", User(1, "sf1", "pass1"),
                CorrelationData(UUID.randomUUID().toString()))
    }

    @Test
    fun test_sendMsg3() {
        rabbitTemplate.convertAndSend("bootTestJava", "bootTestJava", "bootTestJavaMessage",
                CorrelationData(UUID.randomUUID().toString()))
    }

    @Test
    fun test_fanout() {
        rabbitTemplate.convertAndSend("fanoutExchange", null, JSON.toJSONString(User(1, "sf1", "pass1"))
                , CorrelationData(UUID.randomUUID().toString()))
    }

    @Test
    fun test_topic() {
        // topic.a topic.b 发送的key 匹配这种模式的都会转发到相应订阅 routingkey pattern 的 key
//        rabbitTemplate.convertAndSend("topicExchange", "topi.*", "hello world")
//        rabbitTemplate.convertAndSend("topicExchange", "topic.*", "hello world")
        for(i in 0..10) {
            rabbitTemplate.convertAndSend("topicExchange", "sftopicA.#", "abchello world#"+i)
        }
//        TimeUnit.SECONDS.sleep(50)
    }

    @Test
    fun testhandleRpcMsg(){
        var replay= rabbitTemplate.convertSendAndReceive("handleRpcMsg","handleRpcMsg","hello handleRpcMsg")
        log.info("reply message is:"+replay)
    }

    @Test
    fun testAsyncSender(){
        val future=asyncRabbitTemplate.convertSendAndReceive<String>("handleRpcMsg","handleRpcMsg","hello handleRpcMsg")
        future.addCallback(object :ListenableFutureCallback<String>{
            override fun onSuccess(p0: String?) {
                log.info("recieve success:"+p0)
            }

            override fun onFailure(p0: Throwable) {
                log.error("got exception",p0)
            }
        })
    }

}
