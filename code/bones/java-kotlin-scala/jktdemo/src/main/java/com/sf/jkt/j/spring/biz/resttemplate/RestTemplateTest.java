package com.sf.jkt.j.spring.biz.resttemplate;

import com.google.gson.Gson;
import com.sf.jkt.j.spring.biz.exception.controller.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Duration;

/**
 * resttemplate 配置
 *  https://blog.csdn.net/weixin_36586564/article/details/85264813
 *  https://www.cnblogs.com/f-anything/p/10084215.html
 */
public class RestTemplateTest {
    static RestTemplate template= new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(3))
            .setReadTimeout(Duration.ofSeconds(5)).rootUri("http://127.0.0.1:8081").build();
    //        template.getForObject();
    public static void main(String[] args) throws Exception{
        testUri();
    }

    public static void testSimpleRestTemplate(){

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

    /***
     *UriComponents
     * https://blog.csdn.net/jiachunchun/article/details/90235578
     * @throws Exception
     */
    public static void testUri()throws Exception{

        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("http://example.com/hotels/{hotel}/bookings/{booking}").build();
        URI uri = uriComponents.expand("42", "21").encode().toUri();
        System.out.println(uri.toString());

        UriComponents uriComponents2 = UriComponentsBuilder.newInstance()
                .scheme("http").host("example.com").path("/hotels/{hotel}/bookings/{booking}").build()
                .expand("42", "21")
                .encode();
        URI uri2 = uriComponents2.toUri();
        System.out.println(uri2.toString());


    }



}
