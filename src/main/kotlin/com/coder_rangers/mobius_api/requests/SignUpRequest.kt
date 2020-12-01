package com.coder_rangers.mobius_api.requests

import com.coder_rangers.mobius_api.enums.Genre
import com.coder_rangers.mobius_api.validators.annotations.DifferentPersonalAndGuardianEmail
import com.coder_rangers.mobius_api.validators.annotations.OverEighteen
import com.coder_rangers.mobius_api.validators.annotations.ValidPassword
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
    val patientEmail: String,

    @field:NotBlank
    @field:Email
    val guardianEmail: String,

    @field:NotBlank
    @field:ValidPassword
    val password: String,

    val genre: Genre,

    @field:Past
    @field:OverEighteen
    val birthday: LocalDate
)
