package com.coder_rangers.mobius_api.notifications.redis.publishers

import com.coder_rangers.mobius_api.notifications.redis.messages.AbstractMessage
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic

abstract class RedisMessagePublisher<T : AbstractMessage>(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val channelTopic: ChannelTopic,
) : MessagePublisher<T> {
    override fun publish(message: T) =
        redisTemplate.convertAndSend(
            channelTopic.topic,
            message
        )
}
