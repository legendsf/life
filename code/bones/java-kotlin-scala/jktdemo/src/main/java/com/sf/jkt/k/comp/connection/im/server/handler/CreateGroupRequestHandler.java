package com.sf.jkt.k.comp.connection.im.server.handler;

import com.sf.jkt.k.comp.connection.im.protocol.request.CreateGroupRequestPacket;
import com.sf.jkt.k.comp.connection.im.protocol.response.CreateGroupResponsePacket;
import com.sf.jkt.k.comp.connection.im.util.IDUtil;
import com.sf.jkt.k.comp.connection.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> userIdList = msg.getUserIdList();
        List<String> userNameList = new ArrayList<>();
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }
        String groupId = IDUtil.randomId();
        CreateGroupResponsePacket resp = new CreateGroupResponsePacket();
        resp.setSuccess(true);
        resp.setGroupId(groupId);
        resp.setUserNameList(userNameList);
        channelGroup.writeAndFlush(resp);
        System.out.println("群创建成功,id为：" + resp.getGroupId());
        System.out.println("群里面有:" + resp.getUserNameList());
        SessionUtil.bindChannelGroup(groupId, channelGroup);
    }
}
