package com.coder_rangers.mobius_api.error.exceptions

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(BAD_REQUEST)
class BadRequestException(
    override val message: String,
    override val cause: Throwable? = null
) : APIException(message, BAD_REQUEST, cause)
