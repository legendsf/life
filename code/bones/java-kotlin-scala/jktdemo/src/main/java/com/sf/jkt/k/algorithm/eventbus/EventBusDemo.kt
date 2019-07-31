package com.sf.jkt.k.algorithm.eventbus

import com.google.common.base.Strings
import com.google.common.eventbus.DeadEvent
import com.google.common.eventbus.EventBus
import com.google.common.eventbus.Subscribe
import org.apache.commons.io.IOUtils
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket

data class TestEvent(var message: Int) {}
class EventListener {
    var lastMessage = 0
    @Subscribe
    fun listen(event: TestEvent) {
        lastMessage = event.message
        println("receive Message:" + lastMessage)
    }
}

data class IntEvent(var message: Int) {}
data class LongEvent(var message: Long) {}

class MutiListener {
    var intEvent: IntEvent = IntEvent(0)
    var longEvent: LongEvent = LongEvent(0)
    @Subscribe
    fun listenInt(message: IntEvent) {
        this.intEvent = message
        println("receive Int message:" + message)
    }

    @Subscribe
    fun listenLong(message: LongEvent) {
        this.longEvent = message
        println("receive Long message:" + message)
    }

}

class DeadEventListener {
    var notDelivered = false
    @Subscribe
    fun listen(event: DeadEvent) {
        notDelivered = true
        println("received deadEvent:" + event)
    }
}

fun testEventBus() {
    var eventBus = EventBus("test")
    var listener = EventListener()
    eventBus.register(listener)
//    eventBus.post(TestEvent(100))
    eventBus.post(TestEvent(200))
    eventBus.post(TestEvent(300))
}

fun testMutiEventBus() {
    var eventBus = EventBus("muti")
    eventBus.register(MutiListener())
    eventBus.register(DeadEventListener())
    eventBus.post(IntEvent(100))
    eventBus.post(LongEvent(1000))
    eventBus.post(TestEvent(111))

}

class UserThread(var connection: Socket, var channel: EventBus) :
        Thread() {
    lateinit var inbuf: BufferedReader
    lateinit var outPrint: PrintWriter

    init {
        try {
            inbuf = BufferedReader(InputStreamReader(connection.getInputStream()))
            outPrint = PrintWriter(connection.getOutputStream(), true)
        } catch (e: Exception) {
            e.printStackTrace()
            IOUtils.closeQuietly(inbuf)
            IOUtils.closeQuietly(outPrint)
        }
    }

    @Subscribe
    fun receiveMessage(message: String) {
        if (!Strings.isNullOrEmpty(message)) {
            println("received message:" + message)
        }
    }

    override fun run() {
        inbuf.use { it ->
            var input = it.readLine()
            while (input != null) {
                channel.post(input)
                input = it.readLine()
            }
        }
        channel.unregister(this)
        connection.close()
        IOUtils.closeQuietly(inbuf)
        IOUtils.closeQuietly(outPrint)
    }
}

fun testChannel(){
    var channel=EventBus()
    var socket=ServerSocket(4444)
    while (true){
        var connection=socket.accept()
        var userThread=UserThread(connection,channel)
        channel.register(userThread)
        userThread.start()
    }
}

fun main() {
//    testMutiEventBus()
    testChannel()
}

