package com.sf.jkt.k.comp.connection.http.sparkjava

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

fun testOkHttp() {
    val client = OkHttpClient()
    val request = Request.Builder().url("http://www.baidu.com")
            .build()
    val resp = client.newCall(request).execute()
    if (!resp.isSuccessful) {
        throw IOException("服务器端错误:" + resp)
    }

}

fun main() {

    testOkHttp()
}