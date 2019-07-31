package com.sf.jkt.k.comp.connection.http.spring

import com.sf.jkt.k.util.SpringBootBaseTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.client.RestTemplate

class RestTemplateTest :SpringBootBaseTest(){
    @Autowired
    lateinit var restTemplate: RestTemplate

    @Test
    fun testBaidu(){
        var uri=restTemplate.postForLocation("www.baidu.com",null)
        println(uri)
    }
}