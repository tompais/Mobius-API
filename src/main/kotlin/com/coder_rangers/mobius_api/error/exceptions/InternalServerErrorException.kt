package com.coder_rangers.mobius_api.error.exceptions

import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(INTERNAL_SERVER_ERROR)
class InternalServerErrorException(
    override val message: String,
    override val cause: Throwable? = null
) : APIException(message, INTERNAL_SERVER_ERROR, cause)
