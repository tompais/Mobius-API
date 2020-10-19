package com.coder_rangers.mobius_api.mail.clients.implementations

import com.coder_rangers.mobius_api.mail.clients.interfaces.IEmailClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class EmailClient @Autowired constructor(
    private val emailSender: JavaMailSender
) : IEmailClient {
    override fun sendSimpleMessage(to: String, subject: String, text: String) {
        val message = SimpleMailMessage().apply {
            setFrom("noreply@mobiusmind.com")
            setTo(to)
            setSubject(subject)
            setText(text)
        }

        emailSender.send(message)
    }
}
