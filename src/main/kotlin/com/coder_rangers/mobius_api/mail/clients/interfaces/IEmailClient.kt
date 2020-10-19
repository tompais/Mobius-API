package com.coder_rangers.mobius_api.mail.clients.interfaces

interface IEmailClient {
    fun sendSimpleMessage(to: String, subject: String, text: String)
}
