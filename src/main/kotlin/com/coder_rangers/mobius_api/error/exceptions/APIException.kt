package com.coder_rangers.mobius_api.error.exceptions

import com.coder_rangers.mobius_api.config.Slf4jMDCFilterConfig
import org.slf4j.MDC
import org.springframework.http.HttpStatus

open class APIException(
    override val message: String,
    val httpStatus: HttpStatus,
    override val cause: Throwable? = null,
    val requestId: String? = MDC.get(Slf4jMDCFilterConfig.DEFAULT_MDC_UUID_TOKEN_KEY)
) : RuntimeException(message, cause)
