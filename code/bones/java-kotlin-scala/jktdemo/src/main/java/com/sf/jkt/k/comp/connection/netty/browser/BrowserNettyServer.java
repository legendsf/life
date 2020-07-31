package com.sf.jkt.k.comp.connection.netty.browser;

import com.sf.jkt.k.comp.connection.netty.proxy.ByteBufToBytes;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * netty options 参数
 * https://blog.csdn.net/smallcatbaby/article/details/89877617
 *
 * https://www.jianshu.com/p/f05030ffb75d
 *
 * netty 线程模型
 *
 * https://www.jianshu.com/p/738095702b75
 *
 * netty 三种线程模型
 *
 * https://blog.csdn.net/qq9808/article/details/105644640
 *
 * netty 源码
 * https://www.cnblogs.com/imstudy/p/9908791.html
 *
 * Tcp 超时时间
 *
 * https://developer.aliyun.com/article/270260
 *
 * https://zhuanlan.zhihu.com/p/41197705
 *
 * https://ms2008.github.io/2017/04/14/tcp-timeout/
 *
 * https://www.infoq.cn/article/WV30iLpz_fYsDY8dpuyY
 *
 *
 */
public class BrowserNettyServer {
    public static void main(String[] args) throws Exception{
        String str=HttpHeaderNames.CONTENT_TYPE.toString();
        System.out.println(str);
        startServer(8001);
    }

    public static void startServer(int port) throws Exception{
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss,work).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new HttpResponseEncoder())
                                    .addLast(new HttpRequestDecoder())
                                    .addLast(new HttpObjectAggregator(65536))
                                    .addLast(new HttpServerHandler());
                        }
                    });
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        }finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    public static class HttpServerHandler extends ChannelInboundHandlerAdapter{
        private ByteBufToBytes reader;

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            String respMsg="OK OK OK OK";
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HTTP_1_1, OK, Unpooled.wrappedBuffer(respMsg
                    .getBytes()));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE.toString(),"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH.toString(),response.content().readableBytes());
            response.headers().set(HttpHeaderNames.CONNECTION.toString(),HttpHeaderValues.KEEP_ALIVE);
            System.out.println("**************"+respMsg);
            ctx.write(response);
            ctx.flush();
        }



        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//            super.channelReadComplete(ctx);
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//            super.exceptionCaught(ctx,cause);
            ctx.close();
        }
    }

}
