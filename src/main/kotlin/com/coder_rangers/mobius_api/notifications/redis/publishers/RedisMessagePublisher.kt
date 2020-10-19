package com.coder_rangers.mobius_api.notifications.redis.publishers

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic

open class RedisMessagePublisher(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val channelTopic: ChannelTopic,
) : MessagePublisher {
    override fun publish(message: Any) =
        redisTemplate.convertAndSend(
            channelTopic.topic,
            message
        )
}
