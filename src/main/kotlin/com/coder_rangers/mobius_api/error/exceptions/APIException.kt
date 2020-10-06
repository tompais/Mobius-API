package com.coder_rangers.mobius_api.error.exceptions

import com.coder_rangers.mobius_api.config.Slf4jMDCFilterConfiguration
import org.apache.commons.lang3.exception.ExceptionUtils
import org.slf4j.MDC
import org.springframework.http.HttpStatus
import java.util.Date

open class APIException(
    override val message: String,
    val httpStatus: HttpStatus,
    override val cause: Throwable? = null,
    private val requestId: String = MDC.get(Slf4jMDCFilterConfiguration.DEFAULT_MDC_UUID_TOKEN_KEY)
) : RuntimeException(message, cause) {
    fun buildResponse() = mapOf(
        "message" to message,
        "statusCode" to httpStatus.value(),
        "errorCode" to httpStatus.reasonPhrase,
        "originalException" to ExceptionUtils.getRootCauseMessage(this),
        "stackTrace" to ExceptionUtils.getStackFrames(this).map { it.replace("\t", "") },
        "requestId" to requestId,
        "timestamp" to Date()
    )
}
