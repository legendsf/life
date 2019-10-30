package com.sf.jkt.k.web

import javafx.application.Application
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.FilterType


@SpringBootApplication(exclude = [
//    DataSourceAutoConfiguration::class
    KafkaAutoConfiguration::class
    , RabbitAutoConfiguration::class
d
]
)
@ComponentScan(basePackages = ["com.sf"],excludeFilters = [ComponentScan.Filter(type=FilterType.REGEX,
//        pattern = [".*(k|K)afka.*",".*(r|R)abbit.*",".*k.comp.*"]
        pattern = [".*k.comp.*"]
        )])

class KotlinApplication

fun main(args: Array<String>) {
   var ctx= runApplication<KotlinApplication>(*args)
    println("=================MBEANS BEGIN")
//    ctx.beanDefinitionNames.forEach { it->println(it) }
    println("=================MBEANS END")
}

@Bean
fun servletContainer(): TomcatServletWebServerFactory {
    return TomcatServletWebServerFactory(80)
}