package com.sf.jkt.k.comp.connection.http.sparkjava

import com.alibaba.fastjson.JSON
import spark.Request
import spark.Response
import spark.ResponseTransformer
import spark.Route
import spark.Spark.*

fun main() {
    // http://localhost:4567/hello
    get("/hello", object : Route {
        override fun handle(request: Request?, response: Response?): Any {
            return "hello world"
        }
    })
    get("/hello1", { _, _ -> "hello1 world" })
    get("/hello2") { _, _ -> "hello2 world" }
    get("/helloJson", object : Route {
        override fun handle(request: Request?, response: Response?): Any {
            return Resp(msg = "ok-helloJson")
        }
    }, FastJsonTransformer.instance)
    get("/helloJson1", Route { _, _ -> Resp(msg = "ok-helloJson1") }, FastJsonTransformer.instance)
}

class FastJsonTransformer : ResponseTransformer {
    companion object {
        @Volatile
        var instance = FastJsonTransformer()
    }

    override fun render(model: Any): String {
        return JSON.toJSONString(model)
    }
}

data class Resp(var code: Int = 0, var msg: String = "ok", var data: Any? = null) {}
