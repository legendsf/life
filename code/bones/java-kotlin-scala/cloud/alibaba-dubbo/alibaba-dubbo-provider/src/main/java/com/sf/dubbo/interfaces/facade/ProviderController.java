package com.sf.dubbo.interfaces.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    @GetMapping("/feign/echo")
    public String feignEcho(){
        return "hello feignEcho";
    }
}
