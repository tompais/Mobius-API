package com.coder_rangers.mobius_api.notifications.redis.publishers

interface MessagePublisher {
    fun publish(message: Any)
}
