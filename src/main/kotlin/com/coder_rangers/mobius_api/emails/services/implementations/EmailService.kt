package com.coder_rangers.mobius_api.emails.services.implementations

import com.coder_rangers.mobius_api.emails.clients.interfaces.IEmailClient
import com.coder_rangers.mobius_api.emails.services.interfaces.IEmailService
import com.coder_rangers.mobius_api.responses.CompleteTestResult
import com.coder_rangers.mobius_api.utils.MailConstant
import com.coder_rangers.mobius_api.utils.toPDF
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine

@Service
class EmailService @Autowired constructor(
    private val emailClient: IEmailClient,
    private val thymeleafTemplateEngine: SpringTemplateEngine
) : IEmailService {
    override fun sendTestResults(guardianEmails: Set<String>, testResult: CompleteTestResult) {
        thymeleafTemplateEngine.process(
            MailConstant.Template.PATIENT_TEST_RESULTS_HTML_NAME,
            Context().apply {
                setVariables(
                    mapOf(
                        "testResult" to testResult
                    )
                )
            }
        ).toPDF().also { pdfByteArray ->
            emailClient.sendMessageWithAttachment(
                guardianEmails,
                MailConstant.Subject.PATIENT_TEST_RESULTS,
                MailConstant.Attachment.PATIENT_TEST_RESULTS_PDF_NAME,
                pdfByteArray
            )
        }
    }
}
