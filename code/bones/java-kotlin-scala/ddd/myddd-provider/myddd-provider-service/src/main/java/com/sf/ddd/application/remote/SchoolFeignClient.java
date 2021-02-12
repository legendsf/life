package com.sf.ddd.application.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "MYDDD-SCHOOL-PROVIDER")
public interface SchoolFeignClient {
    @GetMapping(value = "/school/byUserId/{id}")
    String  querySchool(@PathVariable("id") String userId);
}
