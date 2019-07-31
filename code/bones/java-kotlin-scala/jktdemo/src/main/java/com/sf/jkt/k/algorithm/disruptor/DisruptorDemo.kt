package com.sf.jkt.k.algorithm.disruptor

import com.lmax.disruptor.*
import com.lmax.disruptor.dsl.Disruptor
import com.lmax.disruptor.dsl.ProducerType
import java.util.concurrent.ThreadFactory

data class MessageEvent(var message: String) {
}

class MessageEventFactory : EventFactory<MessageEvent> {
    override fun newInstance(): MessageEvent {
        return MessageEvent("")
    }
}

class MessageEventTranslator : EventTranslatorOneArg<MessageEvent, String> {
    override fun translateTo(p0: MessageEvent?, p1: Long, p2: String?) {
        p0!!.message = p2!!
    }
}

class ConsumerThreadFactory : ThreadFactory {
    override fun newThread(r: Runnable?): Thread {
        return Thread(r, "Simple Disruptor Consumer Thread")
    }
}

class MessageEventHandler : EventHandler<MessageEvent> {
    override fun onEvent(p0: MessageEvent?, p1: Long, p2: Boolean) {
        println("handle event:" + p0)
    }
}

class MessageExceptionHandler : ExceptionHandler<MessageEvent> {
    override fun handleOnStartException(p0: Throwable?) {
        p0?.printStackTrace()
    }

    override fun handleEventException(p0: Throwable?, p1: Long, p2: MessageEvent?) {
        p0?.printStackTrace()
    }

    override fun handleOnShutdownException(p0: Throwable?) {
        p0?.printStackTrace()
    }
}

class MessageEventProducer(var ringBuffer: RingBuffer<MessageEvent>) {
    fun onData(message: String) {
        var translator = MessageEventTranslator()
        ringBuffer.publishEvent(translator, message)
    }
}

fun testDisruptor() {
    var message = "hello disruptor !"
    var rbSize = 2048
    var disruptor = Disruptor<MessageEvent>(MessageEventFactory(), rbSize, ConsumerThreadFactory(), ProducerType.SINGLE,
            BlockingWaitStrategy())
    disruptor.handleEventsWith(MessageEventHandler())
    disruptor.setDefaultExceptionHandler(MessageExceptionHandler())
    var rb = disruptor.start()
    var producer = MessageEventProducer(rb)
    producer.onData(message)
//    disruptor.shutdown()
}

fun main() {
    testDisruptor()
}