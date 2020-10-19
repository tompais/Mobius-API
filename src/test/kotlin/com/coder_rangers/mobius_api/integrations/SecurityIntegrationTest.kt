package com.coder_rangers.mobius_api.integrations

import com.coder_rangers.mobius_api.database.repositories.IPatientRepository
import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.utils.MockUtils.mockPatient
import com.coder_rangers.mobius_api.utils.MockUtils.mockSignInRequest
import com.coder_rangers.mobius_api.utils.MockUtils.mockSignUpRequest
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.restassured.http.ContentType.JSON
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import java.time.LocalDate

class SecurityIntegrationTest @Autowired constructor(
    private val patientRepository: IPatientRepository
) : BaseIntegrationTest("/security") {

    @MockkBean(relaxed = true)
    private lateinit var javaMailSender: JavaMailSender

    companion object {
        @JvmStatic
        @Suppress("UNUSED")
        fun signUpCases() = listOf(
            Arguments.of(
                mockSignUpRequest(firstName = ""),
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
                mockSignUpRequest(),
                CREATED
            )
        )
    }

    @BeforeEach
    fun setUp() {
        patientRepository.deleteAll()
        every { javaMailSender.send(any<SimpleMailMessage>()) } just runs
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
        patientRepository.saveAndFlush(mockPatient())

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
