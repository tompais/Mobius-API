package com.coder_rangers.mobius_api.utils

import com.coder_rangers.mobius_api.enums.Genre
import com.coder_rangers.mobius_api.enums.Genre.OTHER
import com.coder_rangers.mobius_api.models.Guardian
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.Task
import com.coder_rangers.mobius_api.requests.SignInRequest
import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.utils.TestConstant.GUARDIAN_EMAIL
import com.coder_rangers.mobius_api.utils.TestConstant.NEW_PATIENT_EMAIL
import com.coder_rangers.mobius_api.utils.TestConstant.PASSWORD
import com.coder_rangers.mobius_api.utils.TestConstant.PATIENT_EMAIL
import io.mockk.every
import io.mockk.mockk
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.util.ResourceUtils
import org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX
import java.time.LocalDate
import java.util.Base64

object MockUtils {
    fun mockSignUpRequest(
        firstName: String = "Fulanito",
        lastName: String = "De Tal",
        patientEmail: String = NEW_PATIENT_EMAIL,
        guardianEmail: String = GUARDIAN_EMAIL,
        password: String = PASSWORD,
        genre: Genre = OTHER,
        birthday: LocalDate = LocalDate.now().minusYears(20)
    ) = SignUpRequest(
        firstName,
        lastName,
        patientEmail,
        guardianEmail,
        Base64.getEncoder().encodeToString(password.toByteArray()),
        genre,
        birthday
    )

    fun mockSignInRequest(
        email: String = PATIENT_EMAIL,
        password: String = PASSWORD
    ) = SignInRequest(
        email,
        Base64.getEncoder().encodeToString(password.toByteArray())
    )

    fun getImageFromClasspathInBase64(imageName: String): String =
        ResourceUtils.getURL("${CLASSPATH_URL_PREFIX}images/$imageName").readBytes().let {
            Base64.getEncoder().encodeToString(it)
        }

    fun mockPatient(
        firstName: String = "Pepe",
        lastName: String = "Argento",
        patientEmail: String = "pepeargento@gmail.com",
        password: String = DigestUtils.sha256Hex("moniiiiiii"),
        birthday: LocalDate = LocalDate.now().minusYears(40L),
        guardians: Set<Guardian> = setOf(
            mockk(relaxed = true) {
                every { email } returns GUARDIAN_EMAIL
            }
        ),
        taskResults: MutableSet<Task.Result>? = mutableSetOf()
    ): Patient =
        Patient(
            firstName = firstName,
            lastName = lastName,
            email = patientEmail,
            password = password,
            birthday = birthday,
            taskResults = taskResults,
            guardians = guardians
        )
}
