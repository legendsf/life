package com.sf.jkt.k.comp.connection.http.spring

import org.springframework.web.client.RestTemplate

fun testBaidu(){
    var uri=RestTemplate().getForObject("http://localhost:8082",Any::class.java )
    println(uri)
}

fun main() {
    testBaidu()
}