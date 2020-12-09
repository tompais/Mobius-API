package com.coder_rangers.mobius_api.notifications.redis.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.listener.ChannelTopic

@Configuration
class ChannelTopicConfig {
    @Bean
    fun uploadFileToS3ChannelTopic() = ChannelTopic("upload-file-to-s3-topic")

    @Bean
    fun testFinishedChannelTopic() = ChannelTopic("test-finished-topic")
}
