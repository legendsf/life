package com.sf.jkt.k.comp.connection.im.server.handler;

import com.sf.jkt.k.comp.connection.im.protocol.request.MessageRequestPacket;
import com.sf.jkt.k.comp.connection.im.protocol.response.MessageResponsePacket;
import com.sf.jkt.k.comp.connection.im.session.Session;
import com.sf.jkt.k.comp.connection.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        long begin = System.currentTimeMillis();
        //消息发送session
        Session session = SessionUtil.getSession(ctx.channel());
        MessageResponsePacket resp = new MessageResponsePacket();
        resp.setFromUserId(session.getUserId());
        resp.setFromUserName(session.getUserName());
        resp.setMessage(msg.getMessage());
        Channel toUserChannel = SessionUtil.getChannel(msg.getToUserId());
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(resp).addListener(future -> {
                if (future.isDone()) {
                    System.out.println("消息已发送给对方");
                }
            });
        } else {
            System.out.println("" + session.getUserId() + "不在线，发送失败!");
        }
    }
}
