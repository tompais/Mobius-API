package com.coder_rangers.mobius_api.integrations

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category.ATTENTION
import com.coder_rangers.mobius_api.models.Game.Category.CALCULATION
import com.coder_rangers.mobius_api.models.Game.Category.FIXATION
import com.coder_rangers.mobius_api.models.Game.Category.MEMORY_TEST
import com.coder_rangers.mobius_api.models.Game.Category.ORIENTATION
import com.coder_rangers.mobius_api.models.Game.Category.VISUALIZATION
import com.coder_rangers.mobius_api.requests.PatientTaskAnswers
import com.coder_rangers.mobius_api.requests.categories.AttentionTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.BooleanTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.CalculationTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TextTestGameAnswersRequest
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_ID
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_ID_WITH_FINISHED_TEST
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_WITHOUT_TEST_PROGRESS
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_WITH_TEST_PROGRESS
import io.restassured.http.ContentType.JSON
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
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
                PATIENT_WITHOUT_TEST_PROGRESS,
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
                FIXATION,
                PATIENT_WITH_TEST_PROGRESS,
                OK
            )
        )

        @JvmStatic
        @Suppress("UNUSED")
        fun processGameAnswersCases() = listOf(
            Arguments.of(
                PATIENT_WITHOUT_TEST_PROGRESS,
                BooleanTestGameAnswersRequest(
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
                PATIENT_WITHOUT_TEST_PROGRESS,
                BooleanTestGameAnswersRequest(
                    category = ORIENTATION,
                    gameId = 1,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 1, listOf(true)),
                        PatientTaskAnswers(taskId = 2, listOf(false)),
                        PatientTaskAnswers(taskId = 3, listOf(false)),
                        PatientTaskAnswers(taskId = 4, listOf(false))
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                TextTestGameAnswersRequest(
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
                TextTestGameAnswersRequest(
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
                TextTestGameAnswersRequest(
                    category = FIXATION,
                    gameId = 2,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 1, listOf("bicicleta", "Cuchara", "Manzana")),
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_WITHOUT_TEST_PROGRESS,
                TextTestGameAnswersRequest(
                    category = FIXATION,
                    gameId = 2,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 11, listOf("Bicicleta", "Cuchara", "Coca")),
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                CalculationTestGameAnswersRequest(
                    category = CALCULATION,
                    gameId = 3,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 12, listOf(93)),
                        PatientTaskAnswers(taskId = 13, listOf(86, 79, 72, 65))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                CalculationTestGameAnswersRequest(
                    category = CALCULATION,
                    gameId = 3,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 12, listOf(93)),
                        PatientTaskAnswers(taskId = 1, listOf(86, 74))
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                AttentionTestGameAnswersRequest(
                    category = ATTENTION,
                    gameId = 4,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 14, listOf('o', 'd', 'n', 'u', 'm'))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                AttentionTestGameAnswersRequest(
                    category = ATTENTION,
                    gameId = 4,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 1, listOf('b', 'a', 'M')),
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                BooleanTestGameAnswersRequest(
                    category = MEMORY_TEST,
                    gameId = 5,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 15, listOf(true, false, true))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                BooleanTestGameAnswersRequest(
                    category = MEMORY_TEST,
                    gameId = 5,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 15, listOf(true)),
                        PatientTaskAnswers(taskId = 15, listOf(false)),
                        PatientTaskAnswers(taskId = 15, listOf(false)),
                        PatientTaskAnswers(taskId = 15, listOf(false))
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                TextTestGameAnswersRequest(
                    category = VISUALIZATION,
                    gameId = 6,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 16, listOf("Tigre"))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                TextTestGameAnswersRequest(
                    category = VISUALIZATION,
                    gameId = 6,
                    patientTaskAnswersList = listOf(
                        PatientTaskAnswers(taskId = 11, listOf("Manzana")),
                        PatientTaskAnswers(taskId = 1, listOf("Bicicleta"))
                    )
                ),
                BAD_REQUEST
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
