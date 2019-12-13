package com.sf.jkt.k.comp.connection.im.server.handler;

import com.sf.jkt.k.comp.connection.im.protocol.request.LoginRequestPacket;
import com.sf.jkt.k.comp.connection.im.protocol.response.LoginResponsePacket;
import com.sf.jkt.k.comp.connection.im.session.Session;
import com.sf.jkt.k.comp.connection.im.util.IDUtil;
import com.sf.jkt.k.comp.connection.im.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

//无状态单例模式
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket resp = new LoginResponsePacket();
        resp.setVersion(msg.getVersion());
        resp.setUserName(msg.getUserName());
        if (valid(msg)) {
            resp.setSuccess(true);
            String userId = IDUtil.randomId();
            resp.setUserId(userId);
            System.out.println("" + msg.getUserName() + " 登录成功");
            SessionUtil.bindSession(new Session(userId, msg.getUserName()), ctx.channel());
        } else {
            resp.setReason("账号密码校验失败");
            resp.setSuccess(false);
            System.out.println(new Date() + "登录失败！");
        }
        //返回响应
        ctx.writeAndFlush(resp);
    }

    private boolean valid(LoginRequestPacket req) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
