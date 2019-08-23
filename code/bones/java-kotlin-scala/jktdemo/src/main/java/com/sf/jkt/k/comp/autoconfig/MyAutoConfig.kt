package com.sf.jkt.k.comp.autoconfig

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * https://blog.csdn.net/zxc123e/article/details/80222967
 */

class MHello(var msg: String) {
    fun sayHello(msg: String): String {
        return "hello " + msg
    }
}

@ConfigurationProperties(prefix = "mhello")
 class MHelloProperties(var msg: String="hello") {

}

@Configuration
@EnableConfigurationProperties(MHelloProperties::class)
@ConditionalOnClass(MHello::class)
@ConditionalOnProperty(prefix = "mhello", value = ["enabled"], matchIfMissing = true)
class MHelloAutoConfig {
    @Autowired
    lateinit var mHelloProperties: MHelloProperties

    @Bean
    @ConditionalOnMissingBean(MHello::class)
    fun mhello(): MHello {
        return MHello("Hello "+mHelloProperties.msg)
    }
}