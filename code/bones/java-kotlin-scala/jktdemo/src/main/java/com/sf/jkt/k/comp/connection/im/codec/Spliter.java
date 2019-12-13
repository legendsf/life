package com.sf.jkt.k.comp.connection.im.codec;

import com.sf.jkt.k.comp.connection.im.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class Spliter extends LengthFieldBasedFrameDecoder {

    public Spliter() {
        /***
         * magicNumber: 4
         * version: 1
         * serialAlgrithm:1
         * instruction:1
         * dataLength:4
         * data:N
         */
        super(Integer.MAX_VALUE, 7, 4);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.getInt(in.readerIndex()) != PacketCodec.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
