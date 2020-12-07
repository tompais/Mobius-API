package com.coder_rangers.mobius_api.responses

import com.coder_rangers.mobius_api.enums.TestStatus

data class SignInResponse(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val testStatus: TestStatus
)
