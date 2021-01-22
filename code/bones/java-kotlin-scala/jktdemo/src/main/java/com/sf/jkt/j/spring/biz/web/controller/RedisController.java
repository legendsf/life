package com.sf.jkt.j.spring.biz.web.controller;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping("/redis")
    Object testRedis() {
       redisTemplate.opsForValue().set("k2","v2");
       String value=(String)redisTemplate.opsForValue().get("k2");
       return value;
    }
    @RequestMapping("/redisson")
    Object testRedisson() {
        return "";
    }
}
