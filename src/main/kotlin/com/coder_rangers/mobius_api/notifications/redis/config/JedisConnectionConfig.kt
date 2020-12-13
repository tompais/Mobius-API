package com.coder_rangers.mobius_api.notifications.redis.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory

@Configuration
@Profile("prod")
class JedisConnectionConfig @Autowired constructor(
    @Value("\${REDIS_HOST:localhost}")
    private val hostName: String,

    @Value("\${REDIS_PORT:6379}")
    private val port: Int,

    @Value("\${REDIS_PASSWORD:}")
    private val passwordAsString: String
) {
    @Bean
    fun jedisConnectionFactory() = JedisConnectionFactory(
        RedisStandaloneConfiguration(
            hostName,
            port
        ).apply {
            password = RedisPassword.of(passwordAsString)
        }
    )
}
