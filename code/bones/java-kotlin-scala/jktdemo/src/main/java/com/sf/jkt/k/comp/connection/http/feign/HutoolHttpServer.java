package com.sf.jkt.k.comp.connection.http.feign;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpUtil;

public class HutoolHttpServer {
    public static void main(String[] args) {
       testHttpServer();
    }

    public static void testHttpServer(){
//        HttpUtil.createServer(8002).addAction("/restTest",(request,response)->
//                response.write("{\"id\":1,\"msg\":\"ok\"}", ContentType.JSON.toString())).start();
        HttpUtil.createServer(8002)
                // 返回JSON数据测试
                .addAction("/restTest", (request, response) ->
                        response.write("{\"id\": 1, \"msg\": \"OK\"}", ContentType.JSON.toString())
                ).start();
    }
}
