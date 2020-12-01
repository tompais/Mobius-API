package com.coder_rangers.mobius_api.error.exceptions

class DuplicatedPatientException(
    email: String,
    cause: Throwable? = null
) : ConflictException("The patient with email [$email] already exists.", cause)
