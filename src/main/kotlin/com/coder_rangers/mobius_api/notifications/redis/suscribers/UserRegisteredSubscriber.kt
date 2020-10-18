package com.coder_rangers.mobius_api.notifications.redis.suscribers

import com.coder_rangers.mobius_api.mail.services.implementations.EmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.connection.Message
import org.springframework.stereotype.Service

@Service
class UserRegisteredSubscriber @Autowired constructor(
    private val emailService: EmailService
) : RedisMessageSubscriber() {
    override fun onMessage(message: Message, pattern: ByteArray?) {
        super.onMessage(message, pattern)
        emailService.sendRegistrationConfirmationEmail(message.toString())
    }
}
