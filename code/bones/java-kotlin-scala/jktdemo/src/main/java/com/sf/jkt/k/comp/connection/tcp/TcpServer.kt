package com.sf.jkt.k.comp.connection.tcp

import org.apache.commons.io.IOUtils
import java.io.BufferedInputStream
import java.io.Closeable
import java.io.FileOutputStream
import java.io.OutputStream
import java.net.*

fun main() {
    testBlockTcp()
}

fun testBlockTcp() {
    ServerSocket(6666).use { server ->
        server.accept().use { socket ->
            BufferedInputStream(socket.getInputStream()).use { bis ->
                FileOutputStream("f:/tmp/testo.txt").use { fos ->
                    bis.copyTo(fos)
                    println("接受完成！")
                }
            }
        }
    }
}

fun testBlockTcpFinally(host: String, port: Int) {
    val socket = Socket()
    var os: OutputStream? = null
    try {
        //read/write timedout
        socket.soTimeout = 5000
        //connect timedout
        socket.connect(InetSocketAddress(InetAddress.getByName(host), port), 5000)
        os = socket.getOutputStream()
        os.write("我是客户端请多多关照".toByteArray())
        os.flush()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        IOUtils.closeQuietly()
    }

}