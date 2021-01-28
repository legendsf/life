package com.sf.biz.web.controller.redis;

import com.google.gson.Gson;
import com.sf.biz.web.entity.User;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * redisson 配置
 * https://blog.csdn.net/vistaed/article/details/107026758
 */
@RestController
public class RedisController {
    private Logger logger=LoggerFactory.getLogger(RedisController.class);
    @Autowired
    RedissonClient redissonClient;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/redisTemplate")
    public String redisTemplate(){
        User user=new User();
        user.setId(1);
        user.setName("sf");
        redisTemplate.opsForValue().set(user.getName(),user);
        User user1=(User) redisTemplate.opsForValue().get(user.getName());
        logger.info("user:{}",user1);
        return new Gson().toJson(user1);
    }

    @RequestMapping("/stringRedis")
    public String stringRedis(){
        stringRedisTemplate.opsForValue().set("stringRedis","stringRedis");
        return stringRedisTemplate.opsForValue().get("stringRedis");
    }

    @RequestMapping("/redisson")
    public String redisson(){
        redissonClient.getBucket("redisson").set("redisson");
        return (String) redissonClient.getBucket("redisson").get();
    }
    @RequestMapping("/redisson/lock/{key}")
    public String redissonLock(@PathVariable("key")String lockKey){
        RLock rLock=redissonClient.getLock(lockKey);
        try{
            rLock.lock();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            logger.error("redissonLock",e);
        }finally {
            rLock.unlock();
        }
        return "解锁成功";
    }


}
