package com.coder_rangers.mobius_api.emails.services.interfaces

import com.coder_rangers.mobius_api.responses.CompleteTestResult

interface IEmailService {
    fun sendTestResults(guardianEmails: Set<String>, testResult: CompleteTestResult)
}
