package com.coder_rangers.mobius_api.emails.clients.interfaces

interface IEmailClient {
    fun sendMessageWithAttachment(
        emails: Set<String>,
        subject: String,
        attachmentName: String,
        pdfByteArray: ByteArray,
        text: String = ""
    )
}
