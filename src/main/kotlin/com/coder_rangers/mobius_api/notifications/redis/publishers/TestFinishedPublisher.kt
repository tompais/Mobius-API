package com.coder_rangers.mobius_api.notifications.redis.publishers

import com.coder_rangers.mobius_api.notifications.redis.messages.TestFinishedMessage
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Service

@Service
class TestFinishedPublisher(
    redisTemplate: RedisTemplate<String, Any>,

    @Qualifier("testFinishedChannelTopic")
    channelTopic: ChannelTopic
) : RedisMessagePublisher<TestFinishedMessage>(
    redisTemplate,
    channelTopic
)
