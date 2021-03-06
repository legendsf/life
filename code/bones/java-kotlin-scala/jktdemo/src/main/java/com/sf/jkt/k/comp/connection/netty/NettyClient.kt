package com.sf.jkt.k.comp.connection.netty

import io.netty.bootstrap.Bootstrap
import io.netty.channel.*
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.string.StringEncoder
import org.apache.commons.lang3.RandomUtils
import java.util.*
import java.util.concurrent.TimeUnit

fun testNettyClient() {
    var bootStrap = Bootstrap()
    var group = NioEventLoopGroup()
    bootStrap.group(group)
            .channel(NioSocketChannel::class.java)
            .handler(object : ChannelInitializer<Channel>() {
                override fun initChannel(ch: Channel) {
                    ch.pipeline()
                            .addLast(FirstClientHandler())
                            .addLast(StringEncoder())
                }
            })
    var channel = bootStrap.connect("127.0.0.1", 8000).channel()

    while (true) {
        channel.writeAndFlush("hello world!" + Date())
        TimeUnit.SECONDS.sleep(2)
    }
}

class FirstClientHandler : ChannelInboundHandlerAdapter() {
    override fun channelInactive(ctx: ChannelHandlerContext) {
        println("" + Date() + ":客户端写出数据")
        var buf = ctx.alloc().buffer()
        var bytes = "你好闪电侠".toByteArray(Charsets.UTF_8)
        buf.writeBytes(bytes)
        ctx.channel().writeAndFlush(buf)
    }
}

var MAX_RETRY = 5
fun connect(bootstrap: Bootstrap, host: String, port: Int, retry: Int) {
    bootstrap.option(ChannelOption.SO_KEEPALIVE, true)
            .option(ChannelOption.TCP_NODELAY, true)//立即发送，网络拥堵
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)//链接超时
            .option(ChannelOption.SO_TIMEOUT, 5000)//读取超时时间
    bootstrap.connect(host, port).addListener { future ->
        {
            if (future.isSuccess) {
                println("链接成功!")
            } else if (retry == 0) {
                print("重试次数用完，放弃链接！")
            } else {
                var order = (MAX_RETRY - retry) + 1
                var delay: Long = 1.shl(order).toLong()
                println("第几次重连")
//                var r = Runnable { connect(bootstrap, host, port, retry - 1) }
//                bootstrap.config().group().schedule(r, delay, TimeUnit.SECONDS)
                bootstrap.config().group().schedule({ connect(bootstrap, host, port, retry - 1) },
                        delay, TimeUnit.SECONDS)

            }
        }
    }
}

var clientChannel: Channel? = null

fun closeClient() {
    clientChannel?.close()
}

fun testNettyClient1() {
    var maxRetry = 5
    var bootstrap = Bootstrap()
    var boss = NioEventLoopGroup()
    bootstrap.group(boss)
            .channel(NioSocketChannel::class.java)
            .handler(object : ChannelInitializer<Channel>() {
                override fun initChannel(ch: Channel) {
                    // write out
//                    ch.pipeline().addLast(MessageEncoder())
                    ch.pipeline().addLast(MessageDecoder(1024, 4, 4))
                    ch.pipeline().addLast(MessageClientHandler())
                }
            })
    bootstrap.option(ChannelOption.SO_KEEPALIVE, false)
    var channelFuture = bootstrap.connect("127.0.0.1", 9000)
    var retry = maxRetry
    channelFuture.addListener { futrue ->
        if (futrue.isSuccess) {
            println("连接成功")
        } else if (retry == 0) {
            println("连接重试次数用完，放弃连接!")
        } else {
            var order = maxRetry - retry + 1
            println("" + Date() + "第" + order + "次重连")
            bootstrap.config().group().schedule(object : Runnable {
                override fun run() {
                    bootstrap.connect("127.0.0.1", 9000)
                    retry -= 1
                }
            }, 2, TimeUnit.SECONDS)
        }
    }
    clientChannel = channelFuture.channel()
    //client 写入
//    clientChannel?.pipeline()?.writeAndFlush(msg2buf(createMessage()))
    clientChannel?.writeAndFlush(msg2buf(createMessage()))
//    clientChannel?.eventLoop().schedule(heartBeatMsg)

    //外部其他线程持有该对象可以直接进行client的关闭 //直接关闭
//    clientChannel?.close()
    //异步阻塞
    channelFuture.channel().closeFuture().sync()
    boss.shutdownGracefully()
//    while (true) {
//        channel.pipeline().writeAndFlush(createMessage())
//        TimeUnit.MILLISECONDS.sleep(100)
//    }
}

fun createMessage(): Message {
    var data = "ok"
    var cmdId = RandomUtils.nextInt(0, 65535).toShort()
    var ziped = 0.toByte()
    var type = 0xAF.toByte()
    var message = Message(data, cmdId, type, ziped)
    return message
}

class MessageClientHandler : SimpleChannelInboundHandler<Message>() {
    var count = 0
    override fun channelActive(ctx: ChannelHandlerContext) {
        println("MessageClientHandler.channelActive")
        for (i in 0..100) {
//            ctx.channel().writeAndFlush(createMessage())
//            ctx.pipeline().writeAndFlush(createMessage())
//            ctx.writeAndFlush(createMessage())
        }
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Message?) {
        count++
        println("MessageClientHandler.client.receive.msg:" + msg + ";count:" + count)
        // 如果读到 closeMSG 那么调用 ctx?.close()
//        ctx?.channel()?.close()
        ctx?.close()
    }


    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        println("出现异常关闭连接")
        ctx?.close()
    }
}

//class MessageClientHandler1:SimpleChannelInboundHandler<ByteBuf>(){
//    override fun channelActive(ctx: ChannelHandlerContext?) {
//
//    }
//
//    override fun channelRead0(ctx: ChannelHandlerContext?, msg: ByteBuf?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
//        cause?.printStackTrace()
//        ctx?.close()
//    }
//}

fun main() {
    testNettyClient1()
    println("hello world")
//    clientChannel!!.writeAndFlush(msg2buf(createMessage()))
//    clientChannel!!.writeAndFlush(msg2buf(createMessage()))
//    clientChannel!!.close()
}