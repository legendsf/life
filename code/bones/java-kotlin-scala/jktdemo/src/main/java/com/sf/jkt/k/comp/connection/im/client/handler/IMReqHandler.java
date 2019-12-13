package com.sf.jkt.k.comp.connection.im.client.handler;

import com.sf.jkt.k.comp.connection.im.protocol.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.sf.jkt.k.comp.connection.im.protocol.command.Command.*;

public class IMReqHandler extends SimpleChannelInboundHandler<Packet> {
    public static final IMReqHandler INSTANCE = new IMReqHandler();
    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    {
        handlerMap = new ConcurrentHashMap<>();
        handlerMap.put(LOGIN_RESPONSE, new LoginResponseHandler());
        handlerMap.put(MESSAGE_RESPONSE, new MessageResponseHandler());
        handlerMap.put(CREATE_GROUP_RESPONSE, new CreateGroupResponseHandler());
        handlerMap.put(JOIN_GROUP_RESPONSE, new JoinGroupResponseHandler());
        handlerMap.put(QUIT_GROUP_RESPONSE, new QuitGroupResponseHandler());
        handlerMap.put(LIST_GROUP_MEMBERS_RESPONSE, new ListGroupMembersResponseHandler());
        handlerMap.put(GROUP_MESSAGE_RESPONSE, new GroupMessageResponseHandler());
        handlerMap.put(LOGOUT_RESPONSE, new LogoutResponseHandler());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        ChannelInboundHandler handler = handlerMap.get(msg.getCommand());
        if (handler != null) {
            handler.channelRead(ctx, msg);
        }
    }
}
