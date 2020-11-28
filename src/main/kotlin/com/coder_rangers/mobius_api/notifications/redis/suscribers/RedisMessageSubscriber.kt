package com.coder_rangers.mobius_api.notifications.redis.suscribers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.retry.annotation.Retryable

open class RedisMessageSubscriber : MessageListener {
    protected val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Retryable(value = [Exception::class], maxAttempts = 3)
    override fun onMessage(message: Message, pattern: ByteArray?) = logger.info("Message received: $message")
}
