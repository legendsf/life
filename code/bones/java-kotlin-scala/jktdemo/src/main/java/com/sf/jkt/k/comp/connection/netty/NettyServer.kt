package com.sf.jkt.k.comp.connection.netty

import io.netty.bootstrap.ServerBootstrap
import io.netty.buffer.ByteBuf
import io.netty.channel.*
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.string.StringDecoder
import io.netty.util.AttributeKey
import io.netty.util.ReferenceCountUtil
import io.netty.util.concurrent.Future
import io.netty.util.concurrent.GenericFutureListener

fun testNettyServer() {
    var serverBootstrap = ServerBootstrap()
    var boos = NioEventLoopGroup()
    var worker = NioEventLoopGroup()
    var port = 8000
    serverBootstrap.group(boos, worker)
            .attr(AttributeKey.newInstance("serverName"), "NettyServer")
            .childAttr(AttributeKey.newInstance("ClientKey"), "ClientKey")
            .channel(NioServerSocketChannel::class.java)
            .childOption(ChannelOption.SO_BACKLOG, 24)
            .childOption(ChannelOption.SO_KEEPALIVE, true)//开启TCP默认心跳
            .childHandler(object : ChannelInitializer<NioSocketChannel>() {
                override fun initChannel(ch: NioSocketChannel) {
                    ch.pipeline().addLast(StringDecoder())
                    ch.pipeline().addLast(object : SimpleChannelInboundHandler<String>() {
                        override fun channelRead0(ctx: ChannelHandlerContext, msg: String) {
                            println("receive msg:" + msg)
                        }
                    })
                }
            }).bind(port)
            .addListener {
                object : GenericFutureListener<Future<in Void>> {
                    override fun operationComplete(future: Future<in Void>) {
                        if (future.isSuccess) {
                            println("port 绑定成功!")
                        } else {
                        }
                    }

                }
            }

}

fun bind(sb: ServerBootstrap, port: Int) {
    sb.bind(port).addListener {
        object : GenericFutureListener<Future<in Void>> {
            override fun operationComplete(future: Future<in Void>) {
                if (future.isSuccess) {
                    println("端口[" + port + "]绑定成功!")
                } else {
                    println("端口[" + port + "]绑定失败!")
                    bind(sb, port + 1)
                }
            }
        }
    }
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
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, false)
        bootstrap.option(ChannelOption.SO_BACKLOG, 10000)
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

class FirstServerHandler : ChannelInboundHandlerAdapter() {
    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        var buf = msg!! as ByteBuf
        //release requestMsg
        println("服务端读取到数据:" + buf.toString(Charsets.UTF_8))
        ReferenceCountUtil.safeRelease(buf)
    }
}

class MessageServerHandler : SimpleChannelInboundHandler<Message>() {
    override fun channelRead0(ctx: ChannelHandlerContext, msg: Message) {
        println("server.receive.msg:" + msg)
        msg.data = "hi"
        //从当前写出
//        ctx.writeAndFlush(msg)
        //从过滤连接tail 往head 写
//        ctx.fireChannelRead()//下一个组件释放
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