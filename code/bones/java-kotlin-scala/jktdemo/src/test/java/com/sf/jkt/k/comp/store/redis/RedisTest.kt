package com.sf.jkt.k.comp.store.redis

import com.google.gson.Gson
import com.sf.jkt.k.entity.MUser
import com.sf.jkt.k.web.KotlinApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.test.context.junit4.SpringRunner
import java.util.concurrent.Executors

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [KotlinApplication::class])
public class RedisTest{
    private val log= LoggerFactory.getLogger(this.javaClass)
    @Autowired
    lateinit var redis: StringRedisTemplate
    val gson= Gson()

    @Test
    fun test_asser(){
//        assert(1 ==2)
    }

    @Test
    fun get(){
        val executorService= Executors.newFixedThreadPool(1000)
        redis.opsForValue().set("id1","id1")
        assert(redis.opsForValue().get("id1")== "id1")
        val user= MUser(1,"sfname","sfpass")
        redis.opsForValue().set("MUser:"+user.id,gson.toJson(user))
        val ruser=gson.fromJson(redis.opsForValue().get("MUser:"+user.id),MUser::class.java)
        log.info("ruser:{}",ruser)

    }


}