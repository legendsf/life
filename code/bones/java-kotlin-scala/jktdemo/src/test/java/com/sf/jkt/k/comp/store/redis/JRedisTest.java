package com.sf.jkt.k.comp.store.redis;

import com.google.gson.Gson;
import com.sf.jkt.k.entity.User;
import com.sf.jkt.k.web.KotlinApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {KotlinApplication.class})
public class JRedisTest {
    @Autowired
            private StringRedisTemplate redis;
    Gson gson=new Gson();
    @Test
    public void test_redis() {
        User user = new User(2, "sf", "pass");
        String stru=gson.toJson(user);
        redis.opsForValue().set("user2", stru);
        String obj = redis.opsForValue().get("user2");
        System.out.println(obj);
        User ruser=gson.fromJson(obj,User.class);
        System.out.println(ruser.toString());
    }
}
