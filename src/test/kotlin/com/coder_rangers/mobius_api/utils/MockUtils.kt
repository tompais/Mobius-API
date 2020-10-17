package com.coder_rangers.mobius_api.utils

import com.coder_rangers.mobius_api.enums.Genre
import com.coder_rangers.mobius_api.enums.Genre.OTHER
import com.coder_rangers.mobius_api.models.Guardian
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.User.Status
import com.coder_rangers.mobius_api.models.User.Status.ACTIVE
import com.coder_rangers.mobius_api.requests.SignInRequest
import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.utils.TestConstants.GUARDIAN_EMAIL
import com.coder_rangers.mobius_api.utils.TestConstants.PASSWORD
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_EMAIL
import java.time.LocalDate

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

    fun mockGuardian(
        id: Long = 0,
        firstName: String = "Jaimito",
        lastName: String = "Rodríguez",
        email: String = GUARDIAN_EMAIL,
        password: String = PASSWORD,
        birthday: LocalDate = LocalDate.now().minusYears(24),
        genre: Genre = OTHER,
        status: Status = ACTIVE,
        patients: Set<Patient>? = null
    ) = Guardian(
        id,
        firstName,
        lastName,
        email,
        password,
        birthday,
        genre,
        status,
        patients = patients
    )

    private fun mockSetOfGuardians(numberOfGuardians: Int = 2): Set<Guardian> {
        val guardians = mutableSetOf<Guardian>()

        for (i in 1..numberOfGuardians) {
            guardians.add(
                mockGuardian(
                    firstName = "Jaimito$i",
                    email = "jaimito-$i@gmail.com"
                )
            )
        }

        return guardians
    }

    fun mockPatient(
        id: Long = 0,
        firstName: String = "Fulanito",
        lastName: String = "De Tal",
        email: String = PATIENT_EMAIL,
        password: String = PASSWORD,
        birthday: LocalDate = LocalDate.now().minusYears(20),
        genre: Genre = OTHER,
        status: Status = ACTIVE,
        guardians: Set<Guardian> = mockSetOfGuardians(),
    ) = Patient(
        id,
        firstName,
        lastName,
        email,
        password,
        birthday,
        guardians,
        genre,
        status
    )

    fun mockSignInRequest(
        email: String = PATIENT_EMAIL,
        password: String = PASSWORD
    ) = SignInRequest(
        email,
        password
    )
}
