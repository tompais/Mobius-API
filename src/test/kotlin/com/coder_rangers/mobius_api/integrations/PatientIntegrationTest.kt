package com.coder_rangers.mobius_api.integrations

import com.coder_rangers.mobius_api.models.Category.Type.ORIENTATION
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_ID
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_ID_WITH_FINISHED_TEST
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.OK

class PatientIntegrationTest : BaseIntegrationTest("/patients") {
    companion object {
        @JvmStatic
        @Suppress("UNUSED")
        fun getMentalTestCases() = listOf(
            Arguments.of(
                PATIENT_ID,
                OK
            ),
            Arguments.of(
                321L,
                NOT_FOUND
            ),
            Arguments.of(
                PATIENT_ID_WITH_FINISHED_TEST,
                BAD_REQUEST
            )
        )
    }

    @ParameterizedTest
    @MethodSource("getMentalTestCases")
    fun getMentalTestGameTest(patientId: Long, expectedHttpStatus: HttpStatus) {
        given()
            .queryParam("next-category-type", ORIENTATION)
            .`when`()
            .get("$baseUrl/$patientId/mental-test/game")
            .then()
            .assertThat()
            .status(expectedHttpStatus)
    }
}
