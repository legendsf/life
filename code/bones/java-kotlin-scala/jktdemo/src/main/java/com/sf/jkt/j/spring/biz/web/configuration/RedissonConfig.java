package com.sf.jkt.j.spring.biz.web.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient(){
        Config config=new Config();
        //config.useClusterServers().addNodeAddress("127.0.0.1:6379");
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient= Redisson.create(config);
        return redissonClient;
    }
}
