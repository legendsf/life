package com.sf.jkt.k.comp.connection.im.codec;

import com.sf.jkt.k.comp.connection.im.protocol.Packet;
import com.sf.jkt.k.comp.connection.im.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        out.add(PacketCodec.INSTANCE.decode(msg));
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        ByteBuf buf = ctx.channel().alloc().ioBuffer();
        PacketCodec.INSTANCE.encode(buf, msg);
        out.add(buf);
    }
}
