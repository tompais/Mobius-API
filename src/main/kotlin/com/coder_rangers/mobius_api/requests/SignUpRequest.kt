package com.coder_rangers.mobius_api.requests

import com.coder_rangers.mobius_api.validators.annotations.DifferentPersonalAndGuardianEmail
import com.coder_rangers.mobius_api.validators.annotations.OverEighteen
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Past

@DifferentPersonalAndGuardianEmail
data class SignUpRequest(
    @field:NotBlank
    val firstName: String,

    @field:NotBlank
    val lastName: String,

    @field:NotBlank
    @field:Email
    val personalEmail: String,

    @field:NotBlank
    @field:Email
    val guardianEmail: String,

    @field:Past
    @field:OverEighteen
    val birthday: LocalDate
)
