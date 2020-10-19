package com.coder_rangers.mobius_api.mail.services.interfaces

interface IEmailService {
    fun sendRegistrationConfirmationEmail(to: String)
}
