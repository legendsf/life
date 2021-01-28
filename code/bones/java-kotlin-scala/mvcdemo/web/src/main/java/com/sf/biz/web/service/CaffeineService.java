package com.sf.biz.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@CacheConfig( cacheNames ="caffeine" ,cacheManager = "caffeineCacheManager")
public class CaffeineService {

    @Cacheable(value = "IZUUL", key = "#key")
    public String cacheIZUUL(String key) {
        log.info("cacheIZUUL()方法执行");
        return getCache(key);
    }

    @CachePut(value = "IZUUL", key = "#key")
    public String cachePutIZUUL(String key) {
        log.info("cachePutIZUUL()方法执行");
        return "cachePutIZUUL--" + key;
    }

    @Cacheable(value = "MUMU", key = "#key")
    public String cacheMUMU(String key) {
        log.info("cacheMUMU()方法执行");
        return getCache(key);
    }

    @CachePut(value = "MUMU", key = "#key")
    public String cachePutMUMU(String key) {
        log.info("cachePutMUMU()方法执行");
        return "cachePutMUMU--" + key;
    }

    private String getCache(String key) {
        try {
            log.info("getCache()方法执行");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return key;
    }
}