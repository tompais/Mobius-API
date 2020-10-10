package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.services.interfaces.ISecurityService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SecurityService : ISecurityService {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun signUp(signUpRequest: SignUpRequest) = logger.info(signUpRequest.toString())
}
