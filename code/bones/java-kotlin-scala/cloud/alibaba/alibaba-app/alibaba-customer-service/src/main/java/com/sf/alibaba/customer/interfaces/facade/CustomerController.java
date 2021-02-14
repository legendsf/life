package com.sf.alibaba.customer.interfaces.facade;

import com.sf.alibaba.api.DubboEchoService;
import com.sf.alibaba.customer.application.remote.ProviderFeignClient;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
//
//    @Autowired
//    ProviderFeignClient providerFeignClient;
    @Reference
    DubboEchoService dubboEchoService;
//
//    @GetMapping("/feign")
//    public String helloFeign(){
//        return providerFeignClient.feignEcho("I AM CALLING USING FEIGN");
//    }
//
    @GetMapping("/dubbo")
    public String helloDubbo(){
        return dubboEchoService.echo("I AM CALLING USING DUBBO");
    }

     @GetMapping("/hello")
    public String hello(){
         return "hello customer";
     }


}
