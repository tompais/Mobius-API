package com.coder_rangers.mobius_api.notifications.redis.subscribers

import com.coder_rangers.mobius_api.emails.services.interfaces.IEmailService
import com.coder_rangers.mobius_api.notifications.redis.messages.TestFinishedMessage
import com.coder_rangers.mobius_api.responses.CompleteTestResult
import com.coder_rangers.mobius_api.responses.TestResult.Type.COMPLETE
import com.coder_rangers.mobius_api.services.interfaces.IPatientService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.connection.Message
import org.springframework.stereotype.Service

@Service
class TestFinishedSubscriber @Autowired constructor(
    @Qualifier("camelCase")
    mapper: ObjectMapper,

    private val patientService: IPatientService,
    private val emailService: IEmailService
) : RedisMessageSubscriber(mapper) {
    override fun onMessage(message: Message, pattern: ByteArray?) {
        super.onMessage(message, pattern)
        parseMessage<TestFinishedMessage>(message).let { testFinishedMessage ->
            patientService.getTestResult(testFinishedMessage.patientId, COMPLETE).let { testResult ->
                emailService.sendTestResults(testFinishedMessage.guardianEmails, testResult as CompleteTestResult)
            }
        }
    }
}
