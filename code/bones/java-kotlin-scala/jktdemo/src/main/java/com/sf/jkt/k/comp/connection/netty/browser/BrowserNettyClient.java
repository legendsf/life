package com.sf.jkt.k.comp.connection.netty.browser;

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

public class BrowserNettyClient {
    public static void main(String[] args) throws Exception{
        startClient("127.0.0.1",8001);
    }

    public static void startClient(String host,int port)throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .option(ChannelOption.SO_TIMEOUT,100)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,100)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline=ch.pipeline();
                            pipeline.addLast(new HttpClientCodec());
                            pipeline.addLast(new HttpObjectAggregator(65536));
                            pipeline.addLast(new HttpClientHandler());
                        }
                    });
            ChannelFuture f= b.connect(host,port).sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static class HttpClientHandler extends SimpleChannelInboundHandler<FullHttpResponse>{
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            URI uri = new URI("http://127.0.0.1:8001");
            String msg="are you ok?";
            FullHttpRequest request= new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,HttpMethod.GET,
                    uri.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));
            request.headers().set(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
            ctx.channel().writeAndFlush(request);
        }

//        @Override
//        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//            super.channelRead(ctx, msg);
//        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) throws Exception {
            FullHttpResponse response=msg;
           String contentType= response.headers().get(HttpHeaderNames.CONTENT_TYPE);
            ByteBuf buf=response.content();
            System.out.println("contentType:"+contentType+";msg:"+buf.toString(CharsetUtil.UTF_8));
            ctx.channel().close();
        }
    }
}
