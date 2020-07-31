package com.sf.jkt.k.comp.connection.netty.proxy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * netty httpServer
 *  https://blog.csdn.net/wangshuang1631/article/details/73251180/
 */
public class NettyProxyServer {
    public static void main(String[] args) throws Exception {
        NettyProxyServer.startServer(8000);
    }

    public static void startServer(int port)throws Exception{
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup work=new NioEventLoopGroup();
        try{
            ServerBootstrap b= new ServerBootstrap();
            b.group(boss,work).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new HttpResponseEncoder())
                                    .addLast(new HttpRequestDecoder())
                                    .addLast(new HttpProxyServerHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);
            ChannelFuture f= b.bind(port).sync();

            f.channel().closeFuture().sync();

        }finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }



}
