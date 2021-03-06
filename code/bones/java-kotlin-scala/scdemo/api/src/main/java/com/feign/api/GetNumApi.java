package com.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "provider")
public interface GetNumApi {
    @RequestMapping(value = "/getNum", method = RequestMethod.GET)
    int getRandomInt();
}
