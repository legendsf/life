package com.sf.dubbo.interfaces.facade;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sf.dubbo.api.DemoService;
import com.sf.dubbo.application.remote.feign.ProviderFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RefreshScope
@Slf4j
public class DubboCustomerController {
    static final String demoUrl="dubbo://127.0.0.1:20880";
//    @DubboReference(url=demoUrl)
    @DubboReference()
    DemoService demoService;

    @Autowired
    ProviderFeignClient providerFeignClient;

    @RequestMapping("/feign")
    @SentinelResource(value = "demoFeign",blockHandler ="blockHandler" )
    public String feignEcho(){
        return providerFeignClient.feignEcho();
    }

    @RequestMapping("/hello")
    public String hello() {
        return demoService.hello();
    }

    public String blockHandler(BlockException ex){
        log.info("block:",ex);
        return "common block";
    }
}
