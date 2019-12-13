package com.sf.jkt.k.comp.connection.netty

import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufAllocator
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.util.ReferenceCountUtil
import org.junit.Assert

fun main() {
    test1()
}

fun test1() {
    var buf = ByteBufAllocator.DEFAULT.buffer();
    println(buf.refCnt())
//    buf.retain()
    //c为最后一个组件，所以C释放
    c(b(a(buf)))
    Assert.assertTrue(buf.refCnt() == 0)
}

fun a(input: ByteBuf): ByteBuf {
    input.writeByte(42)
    //下一个组件释放
    return input
}

fun b(input: ByteBuf): ByteBuf {
    try {
        var output = input.alloc().directBuffer(input.readByte() + 1);
        output.writeBytes(input)
        output.writeByte(42)
        //返回的output 交给下一个组件，由下一个组件释放
        return output
    } finally {
        //因为input 没有传给下一个组件，所以要自己释放
        input.release();
    }

}

fun c(input: ByteBuf) {
    ReferenceCountUtil.release(input)
//    input.release();
}

fun test() {
    var buf = ByteBufAllocator.DEFAULT.buffer(9, 100)
    buf.writeBytes(byteArrayOf(1.toByte(), 2.toByte(), 3.toByte(), 4.toByte()))

    var eg = NioEventLoopGroup();
    eg.next().execute(Runnable {
        println("hello")
    })
}