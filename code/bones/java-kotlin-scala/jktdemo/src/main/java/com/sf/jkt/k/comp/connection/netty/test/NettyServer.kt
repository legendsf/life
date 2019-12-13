package com.sf.jkt.k.comp.connection.netty.test

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel

fun main() {
    testStart()
}

fun testStart() {
    var bg = NioEventLoopGroup()
    var wg = NioEventLoopGroup()
    var sb = ServerBootstrap()
    sb.group(bg, wg)
            .channel(NioServerSocketChannel::class.java)
            .childHandler(object : ChannelInitializer<Channel>() {
                override fun initChannel(ch: Channel) {
                    ch.pipeline().addLast(CHI1())
                    ch.pipeline().addLast(CHI2())
                    ch.pipeline().addLast(CHI3())
                    ch.pipeline().addLast(cho1())
                    ch.pipeline().addLast(cho2())
                    ch.pipeline().addLast(cho3())
                }
            })
   sb.bind(8000)
}