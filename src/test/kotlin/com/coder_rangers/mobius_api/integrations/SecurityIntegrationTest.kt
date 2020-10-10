package com.coder_rangers.mobius_api.integrations

import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.utils.MockUtils.mockSignUpRequest
import io.restassured.http.ContentType.JSON
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.OK
import java.time.LocalDate

class SecurityIntegrationTest : BaseIntegrationTest("/security") {

    companion object {
        @JvmStatic
        @Suppress("UNUSED")
        fun invalidSignUpCases() = listOf(
            mockSignUpRequest(firstName = ""),
            mockSignUpRequest(personalEmail = "not an email"),
            mockSignUpRequest(personalEmail = "fulanito@gmail.com", guardianEmail = "fulanito@gmail.com"),
            mockSignUpRequest(birthday = LocalDate.now())
        )
    }

    @ParameterizedTest
    @MethodSource("invalidSignUpCases")
    fun signUpFailsTest(signUpRequest: SignUpRequest) {
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
            .assertThat()
            .statusCode(equalTo(BAD_REQUEST.value()))
    }

    @Test
    fun signUpSuccessfullyTest() {
        val signUpRequest = mockSignUpRequest()

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
            .assertThat()
            .statusCode(equalTo(OK.value()))
    }
}
