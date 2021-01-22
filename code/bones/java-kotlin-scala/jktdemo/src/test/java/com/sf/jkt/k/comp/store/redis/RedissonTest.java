package com.sf.jkt.k.comp.store.redis;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissonTest {
    public static RedissonClient redissonClient(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient= Redisson.create(config);
        return redissonClient;
    }

    @Test
    public void testRedissonWrite()throws Exception{
        RedissonClient redissonClient1=redissonClient();
        RBucket<String> bucket= redissonClient1.getBucket("k3");
        bucket.set("v3");
        System.out.println(bucket.get());
        //异步
        RBucket<String> bucket2=redissonClient1.getBucket("name2");
        bucket2.setAsync("zhaoyun2").get();
        bucket2.getAsync().thenAccept(System.out::println);
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void testRedisson(){
        RedissonClient redissonClient1=redissonClient();
//        RedissonClient redissonClient2=redissonClient();
//        RedissonClient redissonClient3=redissonClient();
        RLock lock1=redissonClient1.getLock("lock1");
        lock1.lock();
        RedissonMultiLock lock=new RedissonMultiLock(lock1);
        try{
            lock.lock();
            System.out.println("operate db ");
        }finally {
            lock.unlock();
        }
    }
}
