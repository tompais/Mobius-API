package com.coder_rangers.mobius_api.notifications.redis.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer

@Configuration
@Profile("prod")
class RedisConfig @Autowired constructor(
    private val jedisConnectionFactory: JedisConnectionFactory
) {
    @Bean
    @Autowired
    fun redisTemplate(
        redisSerializer: GenericJackson2JsonRedisSerializer
    ): RedisTemplate<String, Any> = RedisTemplate<String, Any>().apply {
        setConnectionFactory(jedisConnectionFactory)
        setDefaultSerializer(redisSerializer)
    }

    @Bean
    @Autowired
    fun redisContainer(
        @Qualifier("uploadFileToS3Listener")
        uploadFileToS3Listener: MessageListener,

        @Qualifier("uploadFileToS3ChannelTopic")
        uploadFileToS3ChannelTopic: ChannelTopic,

        @Qualifier("testFinishedListener")
        testFinishedListener: MessageListener,

        @Qualifier("testFinishedChannelTopic")
        testFinishedChannelTopic: ChannelTopic
    ) = RedisMessageListenerContainer().apply {
        setConnectionFactory(jedisConnectionFactory)
        addMessageListener(
            uploadFileToS3Listener,
            uploadFileToS3ChannelTopic
        )
        addMessageListener(
            testFinishedListener,
            testFinishedChannelTopic
        )
    }
}
