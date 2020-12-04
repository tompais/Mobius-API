package com.coder_rangers.mobius_api.responses

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

data class SignInResponse(
    @field:Positive
    val id: Long,

    @field:NotBlank
    val firstName: String,

    @field:NotBlank
    val lastName: String,

    @field:NotBlank
    val token: String
)
