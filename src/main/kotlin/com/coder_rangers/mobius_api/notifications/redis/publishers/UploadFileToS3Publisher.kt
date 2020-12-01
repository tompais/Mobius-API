package com.coder_rangers.mobius_api.notifications.redis.publishers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Service

@Service
class UploadFileToS3Publisher @Autowired constructor(
    redisTemplate: RedisTemplate<String, Any>,

    @Qualifier("uploadFileToS3ChannelTopic")
    channelTopic: ChannelTopic
) : RedisMessagePublisher(
    redisTemplate,
    channelTopic
)
