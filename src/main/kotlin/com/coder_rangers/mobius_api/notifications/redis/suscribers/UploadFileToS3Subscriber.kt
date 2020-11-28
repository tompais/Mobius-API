package com.coder_rangers.mobius_api.notifications.redis.suscribers

import com.coder_rangers.mobius_api.notifications.redis.messages.UploadFileMessage
import com.coder_rangers.mobius_api.services.interfaces.IAmazonS3Service
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.connection.Message
import org.springframework.stereotype.Service

@Service
class UploadFileToS3Subscriber @Autowired constructor(
    @Qualifier("camelCase")
    private val mapper: ObjectMapper,

    private val amazonS3Service: IAmazonS3Service
) : RedisMessageSubscriber() {
    override fun onMessage(message: Message, pattern: ByteArray?) {
        super.onMessage(message, pattern)
        mapper.readValue<UploadFileMessage>(message.toString()).let {
            amazonS3Service.uploadFileToS3(
                it.filePath,
                it.bytes
            )
        }
    }
}
