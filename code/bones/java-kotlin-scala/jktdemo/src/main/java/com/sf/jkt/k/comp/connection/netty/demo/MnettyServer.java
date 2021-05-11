package com.sf.jkt.k.comp.connection.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.nio.charset.StandardCharsets;

public class MnettyServer {
    public  static class MserverHandler extends SimpleChannelInboundHandler{
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println(msg.getClass().getName());
            PooledByteBufAllocator allocator = new PooledByteBufAllocator(false);

            if (msg instanceof HttpRequest) {
                HttpRequest httpRequest = (HttpRequest) msg;
                System.out.println("请求方法名： " + httpRequest.method().name());
                //对不同的路径进行处理
                URI uri = new URI(httpRequest.uri());
                //处理google chrome
                if ("/favicon.ico".equals(uri.getPath())) {
                    System.out.println("请求favicon.ico");
                }
                // 这块就相当于代理，本代理服务器访问百度后返回
                String proxyMsg=cn.hutool.http.HttpUtil.get("https://www.baidu.com");
                proxyMsg="hello server response";

                // 返回的内容
                ByteBuf content = Unpooled.copiedBuffer(proxyMsg, CharsetUtil.UTF_8);
                content=allocator.heapBuffer(proxyMsg.getBytes(CharsetUtil.UTF_8).length);
                content.writeBytes(proxyMsg.getBytes(StandardCharsets.UTF_8));
                // http的响应
                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
                //响应头设置
//                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html");
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
                // 把响应内容写回到客户端
                ctx.writeAndFlush(response);
            }
        }
    }
    public static void main(String[] args) {
        try {
            EventLoopGroup boss = new NioEventLoopGroup();
            EventLoopGroup work= new NioEventLoopGroup();
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler())
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new HttpServerCodec())
                                    .addLast(new HttpObjectAggregator(65536))
                                    .addLast(new MserverHandler());
                        }
                    });
//            ChannelFuture future= bootstrap.bind(8080);
            ChannelFuture future= bootstrap.bind(8080).sync();//阻塞防止客户端退出
            future.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    System.out.println("通道关闭");
                    boss.shutdownGracefully();
                    work.shutdownGracefully();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
