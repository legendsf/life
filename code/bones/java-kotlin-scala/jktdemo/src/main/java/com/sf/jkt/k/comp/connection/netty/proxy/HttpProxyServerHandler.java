package com.sf.jkt.k.comp.connection.netty.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.http.HttpRequest;

public class HttpProxyServerHandler extends ChannelInboundHandlerAdapter {
    private ByteBufToBytes reader;




    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest){
            HttpRequest request=(HttpRequest)msg;
            System.out.println("messageType:"+request.getHeaders().get("messageType"));
            /**
             * 1 getchannel from pool
             * 2 channel.ctx.writeandflush
             * 3.结束操作，等待 bootstrap，找到原始通道进行响应
              */
        }
    }


}
