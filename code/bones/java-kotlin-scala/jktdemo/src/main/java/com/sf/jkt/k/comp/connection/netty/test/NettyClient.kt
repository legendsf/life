package com.sf.jkt.k.comp.connection.netty.test

import io.netty.bootstrap.Bootstrap
import io.netty.channel.*
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import java.util.*

fun main() {
    start()
}

fun start() {
    var group = NioEventLoopGroup()
    var bs = Bootstrap()
    bs.group(group)
            .channel(NioSocketChannel::class.java)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .option(ChannelOption.TCP_NODELAY, true)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .option(ChannelOption.SO_TIMEOUT, 5000)
            .handler(object : ChannelInitializer<Channel>() {
                override fun initChannel(ch: Channel) {
                    ch.pipeline().addLast(CHI1())
                    ch.pipeline().addLast(CHI2())
                    ch.pipeline().addLast(CHI3())
                    ch.pipeline().addLast(cho1())
                    ch.pipeline().addLast(cho2())
                    ch.pipeline().addLast(cho3())
                }
            })
    bs.connect("127.0.0.1", 8000).sync()


}

class CHI1 : ChannelInboundHandlerAdapter() {
    override fun channelActive(ctx: ChannelHandlerContext) {
        println("" + Date() + ":客户端写出数据")
        var buf = ctx.alloc().buffer()
        var bytes = "你好闪电侠".toByteArray(Charsets.UTF_8)
        buf.writeBytes(bytes)
        ctx.channel().writeAndFlush(buf)
    }

    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        println("chi1")
        super.channelRead(ctx, msg)
    }
}

class CHI2 : ChannelInboundHandlerAdapter() {
    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        println("chi2")
        super.channelRead(ctx, msg)
    }
}

class CHI3 : ChannelInboundHandlerAdapter() {
    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        println("chi3")
        super.channelRead(ctx, msg)
    }
}

class cho1 : ChannelOutboundHandlerAdapter() {
    override fun write(ctx: ChannelHandlerContext, msg: Any?, promise: ChannelPromise?) {
        println("" + Date() + ":cho1写出数据")
        var buf = ctx.alloc().buffer()
        var bytes = "cho1写出数据".toByteArray(Charsets.UTF_8)
        buf.writeBytes(bytes)
        ctx.writeAndFlush(buf)
//        ctx.channel().writeAndFlush(buf)
//        ctx.pipeline().writeAndFlush(buf)
    }
}

class cho2 : ChannelOutboundHandlerAdapter() {
    override fun write(ctx: ChannelHandlerContext?, msg: Any?, promise: ChannelPromise?) {
        println("cho2")
        super.write(ctx, msg, promise)
    }
}

class cho3 : ChannelOutboundHandlerAdapter() {
    override fun write(ctx: ChannelHandlerContext?, msg: Any?, promise: ChannelPromise?) {
        println("cho3")
        super.write(ctx, msg, promise)
    }
}

