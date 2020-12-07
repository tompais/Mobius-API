package com.coder_rangers.mobius_api.emails.clients.implementations

import com.coder_rangers.mobius_api.emails.clients.interfaces.IEmailClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class EmailClient @Autowired constructor(
    private val mailSender: JavaMailSender
) : IEmailClient {
    override fun sendMessageWithAttachment(
        emails: Set<String>,
        subject: String,
        attachmentName: String,
        pdfByteArray: ByteArray,
        text: String
    ) {
        mailSender.createMimeMessage().let { mimeMessage ->
            MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
            ).apply {
                setTo(emails.toTypedArray())
                setFrom("noreply@mobiusmind.com")
                setSubject(subject)
                setText(text, true)
                addAttachment(
                    attachmentName,
                    ByteArrayResource(
                        pdfByteArray
                    )
                )
            }
            mailSender.send(mimeMessage)
        }
    }
}
