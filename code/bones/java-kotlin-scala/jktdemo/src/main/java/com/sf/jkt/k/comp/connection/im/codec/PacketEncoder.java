package com.sf.jkt.k.comp.connection.im.codec;

import com.sf.jkt.k.comp.connection.im.protocol.Packet;
import com.sf.jkt.k.comp.connection.im.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodec.INSTANCE.encode(out, msg);
    }
}
