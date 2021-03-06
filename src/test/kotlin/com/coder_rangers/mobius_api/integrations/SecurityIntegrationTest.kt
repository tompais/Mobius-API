package com.coder_rangers.mobius_api.integrations

import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.utils.MockUtils.mockSignInRequest
import com.coder_rangers.mobius_api.utils.MockUtils.mockSignUpRequest
import com.coder_rangers.mobius_api.utils.TestConstant.PATIENT_EMAIL
import io.restassured.http.ContentType.JSON
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import java.time.LocalDate

class SecurityIntegrationTest : BaseIntegrationTest("/security") {
    companion object {
        @JvmStatic
        @Suppress("UNUSED")
        fun signUpCases() = listOf(
            Arguments.of(
                mockSignUpRequest(firstName = ""),
                BAD_REQUEST
            ),
            Arguments.of(
                mockSignUpRequest(password = "lala"),
                BAD_REQUEST
            ),
            Arguments.of(
                mockSignUpRequest(patientEmail = "not an email"),
                BAD_REQUEST
            ),
            Arguments.of(
                mockSignUpRequest(patientEmail = "fulanito@gmail.com", guardianEmail = "fulanito@gmail.com"),
                BAD_REQUEST
            ),
            Arguments.of(
                mockSignUpRequest(birthday = LocalDate.now()),
                BAD_REQUEST
            ),
            Arguments.of(
                mockSignUpRequest(guardianEmail = PATIENT_EMAIL),
                BAD_REQUEST
            ),
            Arguments.of(
                mockSignUpRequest(),
                CREATED
            ),
            Arguments.of(
                mockSignUpRequest(patientEmail = "lala2@gmail.com", guardianEmail = "lala3@gmail.com"),
                CREATED
            )
        )
    }

    @ParameterizedTest
    @MethodSource("signUpCases")
    fun signUpTests(signUpRequest: SignUpRequest, expectedStatus: HttpStatus) {
        given()
            .accept(JSON)
            .contentType(JSON)
            .body(
                mapper.writeValueAsBytes(signUpRequest)
            )
            .`when`()
            .post("$baseUrl/signup")
            .then()
            .log().ifValidationFails()
            .and()
            .assertThat()
            .status(expectedStatus)
    }

    @Test
    fun signInSuccessfullyTest() {
        given()
            .accept(JSON)
            .contentType(JSON)
            .body(
                mapper.writeValueAsBytes(mockSignInRequest())
            )
            .`when`()
            .post("$baseUrl/signin")
            .then()
            .assertThat()
            .status(OK)
    }
}
