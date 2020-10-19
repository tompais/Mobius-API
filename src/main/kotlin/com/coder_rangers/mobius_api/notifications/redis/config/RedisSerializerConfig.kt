package com.coder_rangers.mobius_api.notifications.redis.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer

@Configuration
class RedisSerializerConfig {
    @Bean
    @Autowired
    fun redisSerializer(
        @Qualifier("camelCase")
        mapper: ObjectMapper
    ) = GenericJackson2JsonRedisSerializer(mapper)
}
