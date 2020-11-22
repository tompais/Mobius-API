package com.coder_rangers.mobius_api.integrations

import com.coder_rangers.mobius_api.utils.TestConstants.NON_EXISTENT_PATIENT_ID
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_WITH_TEST_PROGRESS
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.NO_CONTENT

class InternalIntegrationTest : BaseIntegrationTest("/internal") {
    companion object {
        @JvmStatic
        @Suppress("UNUSED")
        fun cleanPatientTestProgressCases() = listOf(
            Arguments.of(
                PATIENT_WITH_TEST_PROGRESS,
                NO_CONTENT
            ),
            Arguments.of(
                NON_EXISTENT_PATIENT_ID,
                NOT_FOUND
            )
        )
    }

    @ParameterizedTest
    @MethodSource("cleanPatientTestProgressCases")
    fun cleanPatientTestProgressTest(patientId: Long, expectedHttpStatus: HttpStatus) {
        given()
            .`when`()
            .post("$baseUrl/patients/$patientId/test-progress/clean")
            .then()
            .assertThat()
            .status(expectedHttpStatus)
    }
}
