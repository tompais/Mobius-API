package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IPatientDAO
import com.coder_rangers.mobius_api.error.exceptions.PatientNotFoundException
import com.coder_rangers.mobius_api.mail.services.interfaces.IEmailService
import com.coder_rangers.mobius_api.requests.SignInRequest
import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.responses.SignInResponse
import com.coder_rangers.mobius_api.services.interfaces.ISecurityService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SecurityService @Autowired constructor(
    private val patientDAO: IPatientDAO,
    private val emailService: IEmailService
) : ISecurityService {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun signUp(signUpRequest: SignUpRequest) {
        logger.info(signUpRequest.toString())
        emailService.sendRegistrationConfirmationEmail("tomas.j.pais@gmail.com")
    }

    override fun signIn(signInRequest: SignInRequest): SignInResponse {
        val patient = patientDAO.findActivePatientByEmailAndPassword("fulanito@gmail.com", "lala1234")
            ?: throw PatientNotFoundException()

        return SignInResponse(
            patient.firstName,
            patient.lastName
        )
    }
}
