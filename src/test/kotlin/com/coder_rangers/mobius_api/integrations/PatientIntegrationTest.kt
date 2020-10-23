package com.coder_rangers.mobius_api.integrations

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category.FIXATION
import com.coder_rangers.mobius_api.models.Game.Category.MEMORY
import com.coder_rangers.mobius_api.models.Game.Category.ORIENTATION
import com.coder_rangers.mobius_api.requests.PatientTaskAnswers
import com.coder_rangers.mobius_api.requests.categories.FixationTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.OrientationTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TestGameAnswersRequest
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_ID
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_ID_WITH_FINISHED_TEST
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_WITHOUT_TEST_PROGRESS
import io.restassured.http.ContentType.JSON
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK

class PatientIntegrationTest : BaseIntegrationTest("/patients") {
    companion object {
        @JvmStatic
        @Suppress("UNUSED")
        fun getMentalTestCases() = listOf(
            Arguments.of(
                ORIENTATION,
                PATIENT_ID,
                OK
            ),
            Arguments.of(
                ORIENTATION,
                321L,
                NOT_FOUND
            ),
            Arguments.of(
                ORIENTATION,
                PATIENT_ID_WITH_FINISHED_TEST,
                BAD_REQUEST
            ),
            Arguments.of(
                MEMORY,
                PATIENT_WITHOUT_TEST_PROGRESS,
                INTERNAL_SERVER_ERROR // TODO: Will throw an exception because mock games are not implemented yet. Please, change it when they are.
            )
        )

        @JvmStatic
        @Suppress("UNUSED")
        fun processGameAnswersCases() = listOf(
            Arguments.of(
                PATIENT_WITHOUT_TEST_PROGRESS,
                OrientationTestGameAnswersRequest(
                    category = ORIENTATION,
                    gameId = 1,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 1, listOf(true)),
                        PatientTaskAnswers(taskId = 2, listOf(false)),
                        PatientTaskAnswers(taskId = 3, listOf(false)),
                        PatientTaskAnswers(taskId = 4, listOf(false)),
                        PatientTaskAnswers(taskId = 5, listOf(false)),
                        PatientTaskAnswers(taskId = 6, listOf(false)),
                        PatientTaskAnswers(taskId = 7, listOf(false)),
                        PatientTaskAnswers(taskId = 8, listOf(false)),
                        PatientTaskAnswers(taskId = 9, listOf(false)),
                        PatientTaskAnswers(taskId = 10, listOf(false))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                FixationTestGameAnswersRequest(
                    category = FIXATION,
                    gameId = 2,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 11, listOf("Bicicleta", "Cuchara", "Manzana")),
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                FixationTestGameAnswersRequest(
                    category = FIXATION,
                    gameId = 2,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 11, listOf("Bicicleta", "Cuchara", "Manzana")),
                        PatientTaskAnswers(taskId = 1, listOf("Bicicleta", "Cuchara", "Manzana")),
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                FixationTestGameAnswersRequest(
                    category = FIXATION,
                    gameId = 2,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 1, listOf("Bicicleta", "Cuchara", "Manzana")),
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_WITHOUT_TEST_PROGRESS,
                FixationTestGameAnswersRequest(
                    category = FIXATION,
                    gameId = 2,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 11, listOf("Bicicleta", "Cuchara", "Coca")),
                    )
                ),
                NO_CONTENT
            )
        )
    }

    @ParameterizedTest
    @MethodSource("getMentalTestCases")
    fun getMentalTestGameTest(nextGameCategory: Game.Category, patientId: Long, expectedHttpStatus: HttpStatus) {
        given()
            .queryParam("next-game-category", nextGameCategory)
            .`when`()
            .get("$baseUrl/$patientId/mental-test/game")
            .then()
            .assertThat()
            .status(expectedHttpStatus)
    }

    @ParameterizedTest
    @MethodSource("processGameAnswersCases")
    fun processGameAnswersTest(
        id: Long,
        testGameAnswersRequest: TestGameAnswersRequest<*>,
        expectedHttpStatus: HttpStatus
    ) {
        given()
            .accept(JSON)
            .contentType(JSON)
            .body(
                mapper.writeValueAsBytes(testGameAnswersRequest)
            )
            .`when`()
            .post("$baseUrl/$id/mental-test/game/answers")
            .then()
            .assertThat()
            .status(expectedHttpStatus)
    }
}
