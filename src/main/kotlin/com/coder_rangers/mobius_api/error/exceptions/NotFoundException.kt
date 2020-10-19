package com.coder_rangers.mobius_api.error.exceptions

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(NOT_FOUND)
open class NotFoundException(
    override val message: String,
    override val cause: Throwable? = null
) : APIException(message, NOT_FOUND, cause)
