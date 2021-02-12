package com.sf.ddd.application.remote;

import com.sf.ddd.third.api.IthirdApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * feign 比较灵活，可以不继承，自己定义方法，做到完全和第三方 API接口解耦
 * 一般 dubboRpc 这种可能需要继承
 */
@FeignClient(value = "MYDDD-THIRDPARTY-PROVIDER")
@Component
public interface ThirdpartyFeignClient extends IthirdApi {
    @Override
    @GetMapping(value = "/hello")
    String hello(String msg);
    @Override
    @GetMapping(value = "/hello/id/{id}")
    String helloId(@PathVariable("id") String id);
    @Override
    @GetMapping(value = "/hello/timeout")
    String helloTimeout();
}
