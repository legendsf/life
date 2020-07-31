package com.sf.jkt.k.comp.connection.netty.browser;

import com.sf.jkt.k.comp.connection.netty.proxy.ByteBufToBytes;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

import java.net.URI;

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
 * chrome 一次 http 请求 netty 执行多次
 * charlse 抓包 localhost 要用 http://charles:8001
 * /etc/hosts : 127.0.0.1 charles
 * 通过 charlse 抓包 favicon.ico,chrome 会自动请求这个 favicon,所以 chrome 会请求两次
 * http://localhost:8081
 * https://www.jb51.net/web/404905.html
 * curl 一次 http 请求执行一次
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
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
//                                     addLast(new HttpResponseEncoder())
//                                    .addLast(new HttpRequestDecoder())
                                    .addLast(new HttpServerCodec())
                                    //多个 http 请求组装成一个 http 请求
                                    .addLast(new HttpObjectAggregator(65536))
                                    .addLast(new TestHttpServerHandler());
                        }
                    });
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        }finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    public static class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

            System.out.println(msg.getClass().getName());

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
                // 返回的内容
                ByteBuf content = Unpooled.copiedBuffer(proxyMsg, CharsetUtil.UTF_8);
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


        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channel active!");
            super.channelActive(ctx);
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channel registered!");
            super.channelRegistered(ctx);
        }

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            System.out.println("handler add!");
            super.handlerAdded(ctx);
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channel inactive!");
            super.channelInactive(ctx);
        }

        @Override
        public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channel unregistered!");
            super.channelUnregistered(ctx);
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
