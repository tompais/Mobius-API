package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.requests.SignInRequest
import com.coder_rangers.mobius_api.requests.SignUpRequest

interface ISecurityService {
    fun signUp(signUpRequest: SignUpRequest)
    fun signIn(signInRequest: SignInRequest)
}
