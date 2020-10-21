package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IPatientDAO
import com.coder_rangers.mobius_api.error.exceptions.PatientNotFoundException
import com.coder_rangers.mobius_api.notifications.redis.publishers.MessagePublisher
import com.coder_rangers.mobius_api.requests.SignInRequest
import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.responses.SignInResponse
import com.coder_rangers.mobius_api.services.interfaces.ISecurityService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SecurityService @Autowired constructor(
    private val patientDAO: IPatientDAO,

    @Qualifier("userRegisteredPublisher")
    private val userRegisteredPublisher: MessagePublisher
) : ISecurityService {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun signUp(signUpRequest: SignUpRequest) {
        logger.info(signUpRequest.toString())
        userRegisteredPublisher.publish(signUpRequest.patientEmail)
    }

    override fun signIn(signInRequest: SignInRequest): SignInResponse {
        val patient = patientDAO.findActivePatientByEmailAndPassword(signInRequest.email, signInRequest.password)
            ?: throw PatientNotFoundException()

        return SignInResponse(
            patient.id,
            patient.firstName,
            patient.lastName
        )
    }
}
