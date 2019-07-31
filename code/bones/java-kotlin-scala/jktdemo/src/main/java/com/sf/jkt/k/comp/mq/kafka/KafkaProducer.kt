package com.sf.jkt.k.comp.mq.kafka

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.ListenableFutureCallback

@Component
class KafkaProducer {
    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String,String>
    private val log= LoggerFactory.getLogger(this.javaClass)

    fun sendMessage(topic:String,any:String){
        var future=kafkaTemplate.send(topic,any)
//        kafkaTemplate.sendDefault()
        future.addCallback(object :ListenableFutureCallback<SendResult<String,String>>{
            override fun onSuccess(p0: SendResult<String, String>?) {
                log.info("发送消息成功:{}",p0)
                //todo 标记哪些数据成功，例如更新数据库，后续定时任务扫描再次触发任务执行
            }

            override fun onFailure(p0: Throwable) {
                log.info("发送失败:{}",p0)
                //todo 重发或者记录数据库
            }
        })
    }
}