package com.sf.jkt.k.comp.connection.tcp

import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket

fun main() {
    testBlockTcpClient()
}

fun testBlockTcpClient(){
    Socket().use {
        socket->
        socket.soTimeout=5000
        socket.connect(InetSocketAddress(InetAddress.getByName("localhost"),6666),5000)
        socket.getOutputStream().use {
            outputStream->
            outputStream.write("hello world".toByteArray())
        }
    }
}