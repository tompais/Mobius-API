package com.coder_rangers.mobius_api.notifications.redis.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer

@Configuration
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
        @Qualifier("userRegisteredListener")
        userRegisteredListener: MessageListener,

        @Qualifier("userRegisteredChannelTopic")
        userRegisteredChannelTopic: ChannelTopic,

        @Qualifier("uploadFileToS3Listener")
        uploadFileToS3Listener: MessageListener,

        @Qualifier("uploadFileToS3ChannelTopic")
        uploadFileToS3ChannelTopic: ChannelTopic
    ) = RedisMessageListenerContainer().apply {
        setConnectionFactory(jedisConnectionFactory)
        addMessageListener(
            userRegisteredListener,
            userRegisteredChannelTopic
        )
        addMessageListener(
            uploadFileToS3Listener,
            uploadFileToS3ChannelTopic
        )
    }
}
