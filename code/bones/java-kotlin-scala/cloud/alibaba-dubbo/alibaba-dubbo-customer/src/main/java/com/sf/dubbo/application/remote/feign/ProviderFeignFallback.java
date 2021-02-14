package com.sf.dubbo.application.remote.feign;

public class ProviderFeignFallback implements ProviderFeignClient {
    @Override
    public String feignEcho() {
        return "from feign fallback";
    }
}
