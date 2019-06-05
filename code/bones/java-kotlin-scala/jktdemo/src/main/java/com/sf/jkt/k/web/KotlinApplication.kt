package com.sf.jkt.k.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude= [DataSourceAutoConfiguration::class])
class KotlinApplication

fun main(args:Array<String>) {
    runApplication<KotlinApplication>(*args)
}

