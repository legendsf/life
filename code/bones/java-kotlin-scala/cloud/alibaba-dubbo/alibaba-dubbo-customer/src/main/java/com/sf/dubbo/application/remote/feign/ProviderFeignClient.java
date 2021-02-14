package com.sf.dubbo.application.remote.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "provider-server",fallback = ProviderFeignFallback.class)
public interface ProviderFeignClient {
    @GetMapping("/feign/echo")
    public String feignEcho();
}
