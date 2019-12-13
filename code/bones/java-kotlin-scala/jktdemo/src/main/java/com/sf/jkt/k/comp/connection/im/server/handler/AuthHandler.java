package com.sf.jkt.k.comp.connection.im.server.handler;

import com.sf.jkt.k.comp.connection.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AuthHandler extends ChannelInboundHandlerAdapter {
    public static final AuthHandler INSTANCE = new AuthHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            //同一个channel 验证一次，后续就不验证了
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }
}
