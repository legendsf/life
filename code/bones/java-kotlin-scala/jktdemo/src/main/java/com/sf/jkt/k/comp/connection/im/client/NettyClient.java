package com.sf.jkt.k.comp.connection.im.client;

import com.sf.jkt.k.comp.connection.im.client.handler.HeartBeatTimerHandler;
import com.sf.jkt.k.comp.connection.im.client.handler.IMReqHandler;
import com.sf.jkt.k.comp.connection.im.codec.PacketCodecHandler;
import com.sf.jkt.k.comp.connection.im.codec.Spliter;
import com.sf.jkt.k.comp.connection.im.console.ConsoleCommandManager;
import com.sf.jkt.k.comp.connection.im.console.LoginConsoleCommand;
import com.sf.jkt.k.comp.connection.im.handler.IMIdleStateHandler;
import com.sf.jkt.k.comp.connection.im.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NettyClient {
    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup work = new NioEventLoopGroup();
        Bootstrap bs = new Bootstrap();
        bs.group(work).channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_TIMEOUT, 15000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(IMReqHandler.INSTANCE);
                        ch.pipeline().addLast(new HeartBeatTimerHandler());
                    }
                });
        connect(bs, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + "连接成功，启动控制台线程...");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.out.println("重试次数已用完，放弃链接！");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                int delay = 1 << order;
                System.out.println("第" + order + "次重连...");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1),
                        delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        ConsoleCommandManager manager = new ConsoleCommandManager();
        LoginConsoleCommand login = new LoginConsoleCommand();
        Scanner sc = new Scanner(System.in);
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    login.exec(sc, channel);
                } else {
                    manager.exec(sc, channel);
                }
            }
        }).start();
    }
}
