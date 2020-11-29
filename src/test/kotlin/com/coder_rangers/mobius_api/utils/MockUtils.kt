package com.coder_rangers.mobius_api.utils

import com.coder_rangers.mobius_api.requests.SignInRequest
import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.utils.TestConstants.GUARDIAN_EMAIL
import com.coder_rangers.mobius_api.utils.TestConstants.PASSWORD
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_EMAIL
import org.springframework.util.ResourceUtils
import java.time.LocalDate
import java.util.Base64

object MockUtils {
    fun mockSignUpRequest(
        firstName: String = "Fulanito",
        lastName: String = "De Tal",
        patientEmail: String = PATIENT_EMAIL,
        guardianEmail: String = GUARDIAN_EMAIL,
        birthday: LocalDate = LocalDate.now().minusYears(20)
    ) = SignUpRequest(
        firstName,
        lastName,
        patientEmail,
        guardianEmail,
        birthday
    )

    fun mockSignInRequest(
        email: String = PATIENT_EMAIL,
        password: String = PASSWORD
    ) = SignInRequest(
        email,
        password
    )

    fun getImageFromClasspathInBase64(imageName: String): String =
        ResourceUtils.getURL("classpath:images/$imageName").readBytes().let {
            Base64.getEncoder().encodeToString(it)
        }
}
