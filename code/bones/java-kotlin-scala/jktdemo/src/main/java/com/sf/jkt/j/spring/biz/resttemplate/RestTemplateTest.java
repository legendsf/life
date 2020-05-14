package com.sf.jkt.j.spring.biz.resttemplate;

import com.google.gson.Gson;
import com.sf.jkt.j.spring.biz.exception.bad.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * resttemplate 配置
 *  https://blog.csdn.net/weixin_36586564/article/details/85264813
 *  https://www.cnblogs.com/f-anything/p/10084215.html
 */
public class RestTemplateTest {
    static RestTemplate template= new RestTemplateBuilder().setConnectTimeout(3000)
            .setReadTimeout(5000).rootUri("http://127.0.0.1:8081").build();
    //        template.getForObject();
    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setAccount("ac");
        String jsonstr=new Gson().toJson(user);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept",MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity=new HttpEntity<String>(jsonstr,headers);
        String s=template.postForEntity("/user/addUser",formEntity,String.class).getBody();
        System.out.println(s);
    }


}
