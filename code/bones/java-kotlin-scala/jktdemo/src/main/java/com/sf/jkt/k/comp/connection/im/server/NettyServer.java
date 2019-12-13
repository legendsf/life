package com.sf.jkt.k.comp.connection.im.server;

import com.sf.jkt.k.comp.connection.im.codec.PacketCodecHandler;
import com.sf.jkt.k.comp.connection.im.codec.Spliter;
import com.sf.jkt.k.comp.connection.im.handler.IMIdleStateHandler;
import com.sf.jkt.k.comp.connection.im.server.handler.AuthHandler;
import com.sf.jkt.k.comp.connection.im.server.handler.HeartBeatRequestHandler;
import com.sf.jkt.k.comp.connection.im.server.handler.IMHandler;
import com.sf.jkt.k.comp.connection.im.server.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.ServerSocket;
import java.util.Date;

public class NettyServer {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        ServerSocket sc;
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();
        final ServerBootstrap sb = new ServerBootstrap();
        sb.group(boss, work).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_REUSEADDR, true)
                //1M
                .option(ChannelOption.SO_RCVBUF, 1048576)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.SO_TIMEOUT, 15000)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        ch.pipeline().addLast(IMHandler.INSTANCE);
                    }
                });
        bind(sb, PORT);
    }

    private static void bind(final ServerBootstrap sb, final int port) {
        sb.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + "端口" + port + "绑定成功!");
            } else {
                System.out.println("端口" + port + "绑定失败");
            }
        });
    }


}
