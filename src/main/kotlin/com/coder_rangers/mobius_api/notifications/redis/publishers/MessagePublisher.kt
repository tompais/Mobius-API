package com.coder_rangers.mobius_api.notifications.redis.publishers

import com.coder_rangers.mobius_api.notifications.redis.messages.AbstractMessage

interface MessagePublisher<T : AbstractMessage> {
    fun publish(message: T)
}
