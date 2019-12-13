package com.sf.jkt.k.comp.connection.im.server.handler;

import com.sf.jkt.k.comp.connection.im.protocol.request.JoinGroupRequestPacket;
import com.sf.jkt.k.comp.connection.im.protocol.response.JoinGroupResponsePacket;
import com.sf.jkt.k.comp.connection.im.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(msg.getGroupId());
        channelGroup.add(ctx.channel());
        JoinGroupResponsePacket resp = new JoinGroupResponsePacket();
        resp.setGroupId(msg.getGroupId());
        ctx.writeAndFlush(resp);
    }
}
