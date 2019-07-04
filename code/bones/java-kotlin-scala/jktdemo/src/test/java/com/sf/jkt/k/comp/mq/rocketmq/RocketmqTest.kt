package com.sf.jkt.k.comp.mq.rocketmq

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer
import org.apache.rocketmq.client.consumer.listener.*
import org.apache.rocketmq.client.producer.*
import org.apache.rocketmq.common.consumer.ConsumeFromWhere
import org.apache.rocketmq.common.message.Message
import org.apache.rocketmq.common.message.MessageExt
import org.apache.rocketmq.common.message.MessageQueue
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel
import org.springframework.test.context.transaction.TransactionalTestExecutionListener
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


fun test_produce() {
    val producer = DefaultMQProducer("OrderProducer")
    producer.namesrvAddr = "127.0.0.1:9876"
    producer.start()

    val tags = arrayOf("createTag", "payTag", "sendTag")
    for (orderId in 1..10) {
        for (type in 0..2) {
            val msg = Message("OrderTopic", tags[type % tags.size], "" + orderId + ":" + type, ("" + orderId + ":" + type).toByteArray())
            val sendResult=producer.send(msg, object:MessageQueueSelector{
                override fun select(mqs: MutableList<MessageQueue>, msg: Message, arg: Any): MessageQueue {
                    val id=arg as Int
                    //同一个订单取模到同一个queue 保持消息顺序
                    val index=id % mqs.size
                    return mqs.get(index)
                }
            },orderId)
            println(sendResult)
        }
    }

}

fun test_consumer(){
    val consumer=DefaultMQPushConsumer("DefaultConsuemr")
    consumer.namesrvAddr="127.0.0.1:9876"
    consumer.consumeFromWhere=ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET
    consumer.subscribe("DefaultCluster","*")
    consumer.messageModel=MessageModel.BROADCASTING
//    consumer.registerMessageListener(this)
    consumer.start()

//    val x=object : MessageListenerOrderly{
//        override fun consumeMessage(p0: MutableList<MessageExt>?, p1: ConsumeOrderlyContext?): ConsumeOrderlyStatus {
//            return Void
//        }
//    }

}


fun test_transaction(){
    val transactionListener=TransactionListenerImpl()
    val producer=TransactionMQProducer("transactionProduceGroup")
    producer.namesrvAddr="127.0.0.1:9876"
    producer.transactionListener=transactionListener
    producer.start()
}

fun test_transaction1(){
    val transactionListener=TransactionListenerImpl()
    val producer=TransactionMQProducer("TransactionMQProducer")
    val executorService=ThreadPoolExecutor(2,5,100,TimeUnit.SECONDS,ArrayBlockingQueue<Runnable>(2000),object:ThreadFactory{
        override fun newThread(r: Runnable?): Thread {
            val t=Thread(r)
            t.name="client-transaction-msg-check-thread"
            return t
        }
    })

    producer.namesrvAddr="127.0.0.1:9876"
    producer.executorService=executorService
    producer.transactionListener=transactionListener
    producer.start()

    for(i in 0..10){
        try {
            val msg = Message("TopicTest-Transaction", "TagA", "KEY" + i, ("Hello RocketMQ Transaction " + i).toByteArray())
            val sendResult = producer.sendMessageInTransaction(msg, i)
        }catch (e: Exception){
            e.printStackTrace()
        }

        for(i in 0..10000){
            Thread.sleep(10000)
        }

    }

    producer.shutdown()
}


class TransactionListenerImpl:TransactionListener{
    override fun executeLocalTransaction(msg: Message?, arg: Any?): LocalTransactionState {
        //set transactionId
        return LocalTransactionState.UNKNOW
    }

    override fun checkLocalTransaction(msg: MessageExt?): LocalTransactionState {
        // 根据message key 或者transactionId 从本地查询状态
      return LocalTransactionState.COMMIT_MESSAGE
    }
}

fun testConsumer(){
    val consumer=DefaultMQPushConsumer("TransactionConsumer")
    consumer.namesrvAddr="127.0.0.1:9876"
    consumer.subscribe("TopicTest-transaction","*")
    consumer.registerMessageListener(object:MessageListenerConcurrently{
        override fun consumeMessage(msgs: MutableList<MessageExt>, context: ConsumeConcurrentlyContext?): ConsumeConcurrentlyStatus {
            //并发读取
            msgs.get(0).msgId
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS
        }
    })
}

fun main() {
    for (i in 1..10) {
        println(i)
    }
}