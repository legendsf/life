package com.sf.jkt.k.comp.connection.http.sparkjava

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RespService {
    @GET("/helloJson1")
    fun getRespService(): Call<ResponseBody>

    @GET("/helloJson")
    fun getResp(): Call<Resp>
}

fun testCall() {
    val retrofit = Retrofit.Builder().baseUrl("http://localhost:4567")
//            .addConverterFactory(GsonConverterFactory.create())
//            //配置回调库，采用RxJava
//            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//            //设置OKHttpClient为网络客户端
//            .client(okHttpClientBuilder.build())
            .build()
    val respService = retrofit.create(RespService::class.java)
    val call = respService.getRespService()
    val resp = call.execute()
    println(resp.body()?.string())
    println("callobj:" + call)
    //异步调用
    call.enqueue(object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, ex: Throwable) {
            ex.printStackTrace()
        }

        override fun onResponse(call: Call<ResponseBody>, resp: Response<ResponseBody>) {
            println("callobj:" + call)
            try {
                if (resp.isSuccessful) {
                    println(resp.body()?.string())
                    //onSuccess(result)
                } else {
                    println("invoke error,code:" + resp.code())
                    //onError
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
//                call.cancel()
            }
        }
    })
//    call.cancel()

}

fun testCallSync() {
    val retrofit = Retrofit.Builder().baseUrl("http://localhost:4567/").addConverterFactory(GsonConverterFactory.create())
            .build()
    val service = retrofit.create(RespService::class.java)
    val resp = service.getResp().execute()
    println(resp.body())
}


fun main() {
    testCallSync()
}