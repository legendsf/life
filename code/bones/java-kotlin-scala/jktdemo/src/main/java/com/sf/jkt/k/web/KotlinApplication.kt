package com.sf.jkt.k.web


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType

/***
 * https://www.jianshu.com/p/2cabfb1975c7
 *
 */

@SpringBootApplication(exclude = [
    DataSourceAutoConfiguration::class,
    KafkaAutoConfiguration::class
    , RabbitAutoConfiguration::class
]
)
@ComponentScan(basePackages = ["com.sf"],excludeFilters = [ComponentScan.Filter(type=FilterType.REGEX,
pattern = ["com.sf.jkt.k.*"]
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