package com.sf.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@EnableDubbo
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class DubboCustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboCustomerApplication.class);
    }
}
