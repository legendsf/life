package com.sf.jkt.k.comp.connection.dubbo.provider

import com.sf.jkt.k.comp.connection.dubbo.consumer.HelloConsumer
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableDubboConfig
//@ComponentScan(basePackages = ["com.sf"])
class ProviderRunner {

}

fun main() {
   val ctx= SpringApplication.run(ProviderRunner::class.java)
   ctx.getBean(HelloConsumer::class.java).hello()

}