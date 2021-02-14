package com.sf.alibaba.customer.application.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "sca-provider")
public interface ProviderFeignClient {
        @GetMapping("/feign/echo")
        String feignEcho(@RequestParam("name")String name);
}
