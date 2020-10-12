package com.coder_rangers.mobius_api.requests

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class SignInRequest(
    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    val password: String
)
