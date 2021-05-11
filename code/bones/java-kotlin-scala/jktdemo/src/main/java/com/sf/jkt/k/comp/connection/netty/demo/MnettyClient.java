package com.sf.jkt.k.comp.connection.netty.demo;

import com.sf.jkt.k.comp.connection.netty.browser.BrowserNettyClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MnettyClient {
    Map<String, Channel> channelMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        startClient("127.0.0.1", 8001);
    }

    public static void startClient(String host, int port) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_TIMEOUT, 100)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 100)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpClientCodec());
                            pipeline.addLast(new HttpObjectAggregator(65536));
                            pipeline.addLast(new BrowserNettyClient.HttpClientHandler());
                        }
                    });

            //可以创建多个连接,连接池管理
            for (int i = 0; i < 100; i++) {
                ChannelFuture f = b.connect("localhost", 8080).sync();
                f.channel().closeFuture().addListener((r) -> {
                    //关闭释放 channel 自身，不要关闭 group，在多连接环境下

                });
                //在每个连接上 先 write 再 read
                /***
                 * 或着
                 * send1 send2 send3
                 * read2 read1 read3 根据流水号等进行匹配
                 * 连接池 动态扩容和空闲连接释放
                 *
                 */
            }
            ChannelFuture f = b.connect(host, port).sync();
            f.addListener(ls -> {
                if (ls.isDone()) {
                    System.out.println("操作完成");//
                    if (ls.isSuccess()) {
                        System.out.println("成功");
                    } else if (ls.isCancelled()) {
                        System.out.println("取消");
                    } else {
                        System.out.println("异常");
                    }
                }
            });
            /***
             * 短线重连
             * map.put(hostport,channel)
             * channel 关掉后 remove
             * 重建：再putmap 此时要加锁
             *      高并发丢失重建会有问题
             *      最好提前建立多个连接进行发送，失败换下一个连接，再失败再重建，异步重建
             * channel.writeandflush(msg)
             *
             */
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static class HttpClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.channel().config().setWriteBufferHighWaterMark(10*1024*1024);
            URI uri = new URI("http://127.0.0.1:8001");
            String msg = "are you ok?";
            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                    uri.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            if(ctx.channel().isWritable()){
                ctx.channel().writeAndFlush(request);
            }else {
                System.out.println("the write queue is too busy");
            }
        }

//        @Override
//        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//            super.channelRead(ctx, msg);
//        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) throws Exception {
            FullHttpResponse response = msg;
            String contentType = response.headers().get(HttpHeaderNames.CONTENT_TYPE);
            ByteBuf buf = response.content();
            System.out.println("contentType:" + contentType + ";msg:" + buf.toString(CharsetUtil.UTF_8));
            ctx.channel().close();
        }
    }
}
