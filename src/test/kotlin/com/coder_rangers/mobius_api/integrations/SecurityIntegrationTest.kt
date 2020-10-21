package com.coder_rangers.mobius_api.integrations

import com.coder_rangers.mobius_api.notifications.redis.publishers.MessagePublisher
import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.utils.MockUtils.mockSignInRequest
import com.coder_rangers.mobius_api.utils.MockUtils.mockSignUpRequest
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_EMAIL
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.justRun
import io.restassured.http.ContentType.JSON
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import java.time.LocalDate

class SecurityIntegrationTest @Autowired constructor(
    @Qualifier("userRegisteredSubscriber")
    private val userRegisteredSubscriber: MessageListener
) : BaseIntegrationTest("/security") {

    @MockkBean(relaxed = true)
    private lateinit var userRegisteredPublisher: MessagePublisher

    @MockkBean(relaxed = true)
    private lateinit var javaMailSender: JavaMailSender

    @MockK
    private lateinit var userRegisteredMessage: Message

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
        every { userRegisteredMessage.toString() } returns PATIENT_EMAIL
        justRun { userRegisteredPublisher.publish(any()) }
        justRun { javaMailSender.send(any<SimpleMailMessage>()) }
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

        if (expectedStatus == CREATED) {
            userRegisteredSubscriber.onMessage(userRegisteredMessage, null)
        }
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
