package com.sf.biz.web.controller.redis;

import com.sf.biz.web.service.CaffeineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaffeineController {

    @Autowired
    private CaffeineService caffeineService;

    @GetMapping("/cache-izuul/{key}")
    public String cacheIZUUL(@PathVariable String key) {

        return caffeineService.cacheIZUUL(key);
    }

    @GetMapping("/cache-put-izuul/{key}")
    public String cachePutIZUUL(@PathVariable String key) {

        return caffeineService.cachePutIZUUL(key);
    }

    @GetMapping("/cache-mumu/{key}")
    public String cacheMUMU(@PathVariable String key) {

        return caffeineService.cacheMUMU(key);
    }

    @GetMapping("/cache-put-mumu/{key}")
    public String cachePutMUMU(@PathVariable String key) {

        return caffeineService.cachePutMUMU(key);
    }
}