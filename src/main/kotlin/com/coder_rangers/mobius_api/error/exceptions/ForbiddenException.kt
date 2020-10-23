package com.coder_rangers.mobius_api.error.exceptions

import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(FORBIDDEN)
open class ForbiddenException(
    message: String,
    cause: Throwable? = null,
) : APIException(message, FORBIDDEN, cause)
