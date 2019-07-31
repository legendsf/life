package com.sf.jkt.k.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.context.annotation.Bean


@SpringBootApplication(exclude= [DataSourceAutoConfiguration::class])
@ComponentScan(basePackages = ["com.sf"])
class KotlinApplication

fun main(args:Array<String>) {
    runApplication<KotlinApplication>(*args)
}

@Bean
fun servletContainer(): TomcatServletWebServerFactory {
    return TomcatServletWebServerFactory(80)
}