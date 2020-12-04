package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.components.interfaces.IJWTGenerator
import com.coder_rangers.mobius_api.error.exceptions.PatientIsAGuardianException
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.SignInRequest
import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.responses.SignInResponse
import com.coder_rangers.mobius_api.services.interfaces.IGuardianService
import com.coder_rangers.mobius_api.services.interfaces.IPatientService
import com.coder_rangers.mobius_api.services.interfaces.ISecurityService
import com.coder_rangers.mobius_api.utils.fromBase64ToSHA256
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SecurityService @Autowired constructor(
    private val patientService: IPatientService,
    private val guardianService: IGuardianService,
    private val jwtGenerator: IJWTGenerator
) : ISecurityService {
    override fun signUp(signUpRequest: SignUpRequest) {
        val guardianEmail = signUpRequest.guardianEmail

        assertGuardianIsNotAPatient(guardianEmail)
        patientService.createOrUpdatePatient(
            Patient(
                firstName = signUpRequest.firstName,
                lastName = signUpRequest.lastName,
                email = signUpRequest.patientEmail,
                birthday = signUpRequest.birthday,
                password = signUpRequest.password.fromBase64ToSHA256(),
                guardians = setOf(
                    guardianService.getOrCreateGuardian(guardianEmail)
                ),
                genre = signUpRequest.genre
            )
        )
    }

    private fun assertGuardianIsNotAPatient(guardianEmail: String) {
        patientService.getActivePatientByEmail(guardianEmail)?.let {
            throw PatientIsAGuardianException(guardianEmail)
        }
    }

    override fun signIn(signInRequest: SignInRequest): SignInResponse =
        patientService.getActivePatientByEmailAndPassword(
            signInRequest.email,
            signInRequest.password.fromBase64ToSHA256()
        ).let { patient ->
            SignInResponse(
                id = patient.id,
                firstName = patient.firstName,
                lastName = patient.lastName,
                token = jwtGenerator.generateJWT(patient.email)
            )
        }
}
