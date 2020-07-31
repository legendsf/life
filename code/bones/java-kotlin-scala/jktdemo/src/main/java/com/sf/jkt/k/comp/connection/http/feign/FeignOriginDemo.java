package com.sf.jkt.k.comp.connection.http.feign;


import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

/**
 * feign 调用
 * https://www.hicode.club/articles/2018/07/16/1550590733941.html
 */
public class FeignOriginDemo {
    public static void main(String[] args) {
        testFeign();
    }

    public static void testFeign(){
        StuApi api= Feign.builder().encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(1000,3500))
                .retryer(new Retryer.Default(5000,5000,3))
                .target(StuApi.class,"http://127.0.0.1:8002/restTest");
        StuMsg stuMsg=api.getStuMsg(1);
        System.out.println(stuMsg);
    }
}
