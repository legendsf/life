package com.sf.jkt.k.comp.store.redis.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.stereotype.Component
import java.io.Serializable

@Configuration
class RedisCacheAutoConfiguration {
    private val log = LoggerFactory.getLogger(this.javaClass)

}

/**
 * 参看原有的health Check:RedisHealthIndicatorConfiguration
 * 该例子不检测redis health
 */
@Component("redisHealthIndicator")
class RedisHealthIndicator : HealthIndicator {
    override fun health(): Health {
        return Health.up().build()
    }
}
