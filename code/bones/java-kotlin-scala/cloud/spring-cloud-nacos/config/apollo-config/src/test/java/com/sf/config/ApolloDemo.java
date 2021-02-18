package com.sf.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

import java.util.concurrent.TimeUnit;

public class ApolloDemo {
    public static void main(String[] args) {
        systemSet();
        testApollo();
    }
    public static void systemSet(){
        System.setProperty("app.id","apollo-config");
        System.setProperty("env","dev");
        System.setProperty("apollo.cluster","SHAJQ");
        System.setProperty("dev_meta","http://localhost:8080");

    }
    public static void testApollo(){
        final Config appConfig = ConfigService.getConfig("nacos-config");
        System.out.println(appConfig.getProperty("demo.user.name", ""));
        System.out.println(appConfig.getProperty("demo.user.age", ""));
        while (true){
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(appConfig.getProperty("demo.user.name", ""));
                System.out.println(appConfig.getProperty("demo.user.age", ""));
                Config cc = ConfigService.getConfig("micro_service.springboot-http");
                System.out.println(cc.getProperty("spring.msg", "nothing"));
                System.out.println(cc.getProperty("spring.hellok", "nothing"));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }
}
