package com.coder_rangers.mobius_api.notifications.redis.publishers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Service

@Service
class UserRegisteredPublisher @Autowired constructor(
    redisTemplate: RedisTemplate<String, Any>,

    @Qualifier("userRegisteredChannelTopic")
    channelTopic: ChannelTopic
) : RedisMessagePublisher(
    redisTemplate,
    channelTopic
)
