package com.sf.jkt.k.comp.connection.dubbo.consumer

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.server.ConfigurableWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableDubboConfig
class ConsumerRunner{
    @Bean
    fun webServerFactoryCustomizer():WebServerFactoryCustomizer<ConfigurableWebServerFactory>{
        return WebServerFactoryCustomizer {
            it.setPort(8082)
        }
    }

}

fun main() {
    val ctx=SpringApplication.run(ConsumerRunner::class.java)
    ctx.getBean(HelloConsumer::class.java).hello()
}