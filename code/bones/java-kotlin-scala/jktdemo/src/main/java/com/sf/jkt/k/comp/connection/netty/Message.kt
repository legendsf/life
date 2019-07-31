package com.sf.jkt.k.comp.connection.netty

import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufAllocator
import io.netty.buffer.UnpooledByteBufAllocator
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.MessageToByteEncoder
import org.apache.commons.compress.archivers.zip.ZipUtil

/**
 * data 要发送的数据
 * cmdId 业务编号
 * type 0xAF 心跳包 0xBF 超时包 0xCF 业务包
 * zip 0 否 1 是
 *
 */
data class Message(var data: String, var cmdId: Short, var type: Byte, var zip: Byte=0.toByte()) {
}


class MessageEncoder : MessageToByteEncoder<Message>() {
    var charset = Charsets.UTF_8
    var compressLength = 1024
    override fun encode(ctx: ChannelHandlerContext, msg: Message, buf: ByteBuf) {
        println("MessageEncoder")
        var source = msg.data
        var body = source.toByteArray(charset)
        buf.writeShort(msg.cmdId.toInt())
        buf.writeByte(msg.type.toInt())
        buf.writeByte(msg.zip.toInt())
        buf.writeInt(body.size)
        buf.writeBytes(body)
    }
}

fun msg2buf(msg:Message):ByteBuf{
    var buf=UnpooledByteBufAllocator.DEFAULT.buffer()
    buf.writeShort(msg.cmdId.toInt())
    buf.writeByte(msg.type.toInt())
    buf.writeByte(msg.zip.toInt())
    var body=msg.data.toByteArray(Charsets.UTF_8)
    buf.writeInt(body.size)
    buf.writeBytes(body)
    return buf
}

class MessageDecoder : LengthFieldBasedFrameDecoder {
    /**
     * cmdId(2)+type(1)+zip(1)+body(4)
     */
    var HEADER_SIZE = 8

    constructor(maxFrameLength: Int, lengthFieldOffset: Int, lengthFieldLength: Int) : super(maxFrameLength,
            lengthFieldOffset, lengthFieldLength) {

    }


    override fun decode(ctx: ChannelHandlerContext?, buf: ByteBuf?): Any? {
        println("MessageDecoder")
        if (buf == null) {
            return null
        }
        //消息头解析不完整，不解析返回null,外面有个for循环，等待下一次数据包过来
        if (buf.readableBytes() < HEADER_SIZE) {
            return null
        }
        buf.markReaderIndex()
        //2 bytes
        var cmdId = buf.readShort()
        var type = buf.readByte()
        var zip = buf.readByte()
        var dataLength = buf.readInt()
        //根据数据头的数据长度剩余的可读字节数小于报文头对应的长度那么也返回null,同时读的index 到原位置
        if (buf.readableBytes() < dataLength) {
            //断包处理查看：ByteToMessageDecoder channelRead 方法
            buf.resetReaderIndex()
            return null
        }
        var data = ByteArray(dataLength)
        buf.readBytes(data)
        var body = String(data, Charsets.UTF_8)
        return Message(body, cmdId, type)
    }
}


