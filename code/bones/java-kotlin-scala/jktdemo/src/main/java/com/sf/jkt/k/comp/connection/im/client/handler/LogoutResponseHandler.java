package com.sf.jkt.k.comp.connection.im.client.handler;

import com.sf.jkt.k.comp.connection.im.protocol.response.LogoutResponsePacket;
import com.sf.jkt.k.comp.connection.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
