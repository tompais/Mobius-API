package com.coder_rangers.mobius_api.utils

import com.coder_rangers.mobius_api.requests.SignUpRequest
import java.time.LocalDate

object MockUtils {
    fun mockSignUpRequest(
        firstName: String = "Fulanito",
        lastName: String = "De Tal",
        personalEmail: String = "personal@gmail.com",
        guardianEmail: String = "guardian@gmail.com",
        birthday: LocalDate = LocalDate.now().minusYears(20)
    ) = SignUpRequest(
        firstName,
        lastName,
        personalEmail,
        guardianEmail,
        birthday
    )
}
