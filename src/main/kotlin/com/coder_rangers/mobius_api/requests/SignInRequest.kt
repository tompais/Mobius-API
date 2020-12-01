package com.coder_rangers.mobius_api.requests

import com.coder_rangers.mobius_api.validators.annotations.ValidPassword
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class SignInRequest(
    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    @field:ValidPassword
    val password: String
)
