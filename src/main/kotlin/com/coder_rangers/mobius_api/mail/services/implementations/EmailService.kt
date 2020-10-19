package com.coder_rangers.mobius_api.mail.services.implementations

import com.coder_rangers.mobius_api.mail.clients.interfaces.IEmailClient
import com.coder_rangers.mobius_api.mail.services.interfaces.IEmailService
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val emailClient: IEmailClient
) : IEmailService {
    override fun sendRegistrationConfirmationEmail(to: String) {
        emailClient.sendSimpleMessage(
            to,
            "Bienvenido a Mobius Mind",
            "hola"
        )
    }
}
