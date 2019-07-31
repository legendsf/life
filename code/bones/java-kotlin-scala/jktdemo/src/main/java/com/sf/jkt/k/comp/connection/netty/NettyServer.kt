package com.sf.jkt.k.comp.connection.netty

import io.netty.bootstrap.ServerBootstrap
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.*
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.string.StringDecoder
import io.netty.util.CharsetUtil
import org.springframework.messaging.support.ChannelInterceptor

fun testNettyServer() {
    var serverBootstrap = ServerBootstrap()
    var boos = NioEventLoopGroup()
    var worker = NioEventLoopGroup()
    serverBootstrap.group(boos, worker)
            .channel(NioServerSocketChannel::class.java)
            .childHandler(object : ChannelInitializer<NioSocketChannel>() {
                override fun initChannel(ch: NioSocketChannel) {
                    ch.pipeline().addLast(StringDecoder())
                    ch.pipeline().addLast(object : SimpleChannelInboundHandler<String>() {
                        override fun channelRead0(ctx: ChannelHandlerContext, msg: String) {
                            println("receive msg:" + msg)
                        }
                    })
                }
            }).bind(8000)
}

var serverChannel: Channel? = null

fun closeServer() {
    serverChannel?.close()
}


fun testNettyServer1() {
    var boss = NioEventLoopGroup()
    var work = NioEventLoopGroup()
    try {
        var bootstrap = ServerBootstrap()
        bootstrap.group(boss, work)
                .channel(NioServerSocketChannel::class.java)
                .childHandler(object : ChannelInitializer<Channel>() {
                    override fun initChannel(ch: Channel) {
                        ch.pipeline().addLast(MessageDecoder(1024, 4, 4))
                        ch.pipeline().addLast(MessageServerHandler())
                        ch.pipeline().addLast(MessageEncoder())
                    }
                })
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE,false)
        bootstrap.option(ChannelOption.SO_BACKLOG,10000)
        var channelFuture = bootstrap.bind(9000).sync()
        //获取serverChannel
        serverChannel = channelFuture.channel()
        channelFuture.channel().closeFuture().sync()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        work.shutdownGracefully()
        boss.shutdownGracefully()
    }
}

class MessageServerHandler : SimpleChannelInboundHandler<Message>() {
    override fun channelRead0(ctx: ChannelHandlerContext, msg: Message) {
        println("server.receive.msg:" + msg)
        msg.data = "hi"
        //从当前写出
//        ctx.writeAndFlush(msg)
        //从过滤连接tail 往head 写
        ctx.pipeline().writeAndFlush(msg)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        println("出现异常关闭连接：")
        //关闭某个客户端的channel
        ctx?.close()
        // 关闭serverChannel
//        ctx.channel().parent().close();
    }
}

fun main() {
    testNettyServer1()
}