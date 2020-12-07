package com.coder_rangers.mobius_api.notifications.redis.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer

@Configuration
class MessageListenerConfig @Autowired constructor(
    private val redisSerializer: GenericJackson2JsonRedisSerializer
) {
    private fun buildMessageListener(messageListener: MessageListener) = MessageListenerAdapter(messageListener).apply {
        setSerializer(redisSerializer)
    }

    @Bean
    @Autowired
    fun uploadFileToS3Listener(
        @Qualifier("uploadFileToS3Subscriber")
        uploadFileToS3Subscriber: MessageListener
    ) = buildMessageListener(uploadFileToS3Subscriber)

    @Bean
    @Autowired
    fun testFinishedListener(
        @Qualifier("testFinishedSubscriber")
        testFinishedSubscriber: MessageListener
    ) = buildMessageListener(testFinishedSubscriber)
}
