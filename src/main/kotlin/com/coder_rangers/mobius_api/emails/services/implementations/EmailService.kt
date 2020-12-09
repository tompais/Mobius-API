package com.coder_rangers.mobius_api.emails.services.implementations

import com.coder_rangers.mobius_api.dto.WeeklyReportDTO
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
        buildAndSendMessageWithAttachment(
            MailConstant.Template.PATIENT_TEST_RESULTS_HTML,
            mapOf("testResult" to testResult),
            guardianEmails,
            MailConstant.Subject.PATIENT_TEST_RESULTS,
            MailConstant.Attachment.PATIENT_TEST_RESULTS_PDF
        )
    }

    override fun sendWeeklyReports(guardianEmails: Set<String>, weeklyReport: WeeklyReportDTO) {
        buildAndSendMessageWithAttachment(
            MailConstant.Template.PATIENT_WEEKLY_REPORT_HTML,
            mapOf("weeklyReport" to weeklyReport),
            guardianEmails,
            MailConstant.Subject.PATIENT_WEEKLY_REPORT,
            MailConstant.Attachment.PATIENT_WEEKLY_REPORT_PDF
        )
    }

    private fun buildAndSendMessageWithAttachment(
        template: String,
        contextVariables: Map<String, Any>,
        emails: Set<String>,
        subject: String,
        attachment: String
    ) {
        buildAttachment(template, contextVariables).also { pdfByteArray ->
            sendMessageWithAttachment(emails, subject, attachment, pdfByteArray)
        }
    }

    private fun sendMessageWithAttachment(
        emails: Set<String>,
        subject: String,
        attachment: String,
        pdfByteArray: ByteArray
    ) {
        emailClient.sendMessageWithAttachment(
            emails,
            subject,
            attachment,
            pdfByteArray
        )
    }

    private fun buildAttachment(
        template: String,
        contextVariables: Map<String, Any>
    ) = thymeleafTemplateEngine.process(
        template,
        Context().apply {
            setVariables(
                contextVariables
            )
        }
    ).toPDF()
}
