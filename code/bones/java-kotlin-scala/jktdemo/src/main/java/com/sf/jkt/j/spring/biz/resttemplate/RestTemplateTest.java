package com.sf.jkt.j.spring.biz.resttemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {
    public static void main(String[] args) {
        RestTemplate template= new RestTemplateBuilder().setConnectTimeout(3000)
                .setReadTimeout(5000).rootUri("http://127.0.0.1:8081").build();
//        template.getForObject();
    }


}
