package com.sf.jkt.j.spring.biz.web;

import cn.hutool.http.HttpUtil;

public class webDemo {
    public static void main(String[] args) {
        HttpUtil.createServer(8081)
                .addAction("/test",(req,res)->{
                    System.out.println("access 8081");
                    res.write("welcome 8081");
                })
                .start();
        HttpUtil.createServer(8082)
                .addAction("/test",(req,res)->{
                    System.out.println("access 8082");
                    res.write("welcome 8082");
                })
                .start();
    }

}
