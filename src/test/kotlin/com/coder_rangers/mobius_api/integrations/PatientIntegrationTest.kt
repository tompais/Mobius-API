package com.coder_rangers.mobius_api.integrations

import com.coder_rangers.mobius_api.models.AnswerWithResult
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category.ATTENTION
import com.coder_rangers.mobius_api.models.Game.Category.CALCULATION
import com.coder_rangers.mobius_api.models.Game.Category.COMPREHENSION
import com.coder_rangers.mobius_api.models.Game.Category.FIXATION
import com.coder_rangers.mobius_api.models.Game.Category.MEMORY
import com.coder_rangers.mobius_api.models.Game.Category.ORIENTATION
import com.coder_rangers.mobius_api.models.Game.Category.READING
import com.coder_rangers.mobius_api.models.Game.Category.REPETITION
import com.coder_rangers.mobius_api.models.Game.Category.VISUALIZATION
import com.coder_rangers.mobius_api.models.Game.Category.WRITING
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.AttentionTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.NumericTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TextTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TextTestGameAnswersWithResultsRequest
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_ID
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_ID_WITH_FINISHED_TEST
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_WITHOUT_TEST_PROGRESS
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_WITH_TEST_PROGRESS
import com.coder_rangers.mobius_api.utils.TestConstants.WRONG_PATIENT_ID
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
                WRONG_PATIENT_ID,
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
                TextTestGameAnswersWithResultsRequest(
                    category = ORIENTATION,
                    gameId = 1,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 1, listOf(AnswerWithResult(true, "lala"))),
                        PatientTaskAnswersRequest(taskId = 2, listOf(AnswerWithResult(false, "lala"))),
                        PatientTaskAnswersRequest(taskId = 3, listOf(AnswerWithResult(false, "lala"))),
                        PatientTaskAnswersRequest(taskId = 4, listOf(AnswerWithResult(false, "lala"))),
                        PatientTaskAnswersRequest(taskId = 5, listOf(AnswerWithResult(false, "lala"))),
                        PatientTaskAnswersRequest(taskId = 6, listOf(AnswerWithResult(false, "lala"))),
                        PatientTaskAnswersRequest(taskId = 7, listOf(AnswerWithResult(false, "lala"))),
                        PatientTaskAnswersRequest(taskId = 8, listOf(AnswerWithResult(false, "lala")))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_WITHOUT_TEST_PROGRESS,
                TextTestGameAnswersWithResultsRequest(
                    category = ORIENTATION,
                    gameId = 1,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 1, listOf(AnswerWithResult(true, "lala"))),
                        PatientTaskAnswersRequest(taskId = 2, listOf(AnswerWithResult(false, "lala"))),
                        PatientTaskAnswersRequest(taskId = 3, listOf(AnswerWithResult(false, "lala"))),
                        PatientTaskAnswersRequest(taskId = 4, listOf(AnswerWithResult(false, "lala")))
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                TextTestGameAnswersRequest(
                    category = FIXATION,
                    gameId = 2,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 9, listOf("Bicicleta", "Cuchara", "Manzana")),
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                TextTestGameAnswersRequest(
                    category = FIXATION,
                    gameId = 2,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 9, listOf("Bicicleta", "Cuchara", "Manzana")),
                        PatientTaskAnswersRequest(taskId = 1, listOf("Bicicleta", "Cuchara", "Manzana")),
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                TextTestGameAnswersRequest(
                    category = FIXATION,
                    gameId = 2,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 1, listOf("bicicleta", "Cuchara", "Manzana")),
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_WITHOUT_TEST_PROGRESS,
                TextTestGameAnswersRequest(
                    category = FIXATION,
                    gameId = 2,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 9, listOf("Bicicleta", "Cuchara", "Coca")),
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                NumericTestGameAnswersRequest(
                    category = CALCULATION,
                    gameId = 3,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 10, listOf(93, 86, 79, 72, 65))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                NumericTestGameAnswersRequest(
                    category = CALCULATION,
                    gameId = 3,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 10, listOf(93)),
                        PatientTaskAnswersRequest(taskId = 1, listOf(86, 74))
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                AttentionTestGameAnswersRequest(
                    category = ATTENTION,
                    gameId = 4,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 11, listOf('o', 'd', 'n', 'u', 'm'))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                AttentionTestGameAnswersRequest(
                    category = ATTENTION,
                    gameId = 4,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 1, listOf('b', 'a', 'M')),
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                TextTestGameAnswersWithResultsRequest(
                    category = MEMORY,
                    gameId = 5,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(
                            taskId = 12,
                            listOf(
                                AnswerWithResult(true, "lala"),
                                AnswerWithResult(false, "lala"),
                                AnswerWithResult(true, "lala")
                            )
                        )
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                TextTestGameAnswersRequest(
                    category = VISUALIZATION,
                    gameId = 6,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 13, listOf("Tigre"))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                TextTestGameAnswersRequest(
                    category = VISUALIZATION,
                    gameId = 6,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 9, listOf("Manzana")),
                        PatientTaskAnswersRequest(taskId = 1, listOf("Bicicleta"))
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                TextTestGameAnswersRequest(
                    category = REPETITION,
                    gameId = 7,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 14, listOf("El flan tiene frutillas y frambuesas"))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                TextTestGameAnswersRequest(
                    category = COMPREHENSION,
                    gameId = 8,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 15, listOf("triangulo", "cuadrado", "circulo"))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                NumericTestGameAnswersRequest(
                    category = READING,
                    gameId = 9,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 16, listOf(4))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                TextTestGameAnswersRequest(
                    category = WRITING,
                    gameId = 10,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 17, listOf("Si llueve mucho, entra agua por el tejado"))
                    )
                ),
                NO_CONTENT
            )
        )

        @JvmStatic
        @Suppress("UNUSED")
        fun getTestResultCases() = listOf(
            Arguments.of(
                WRONG_PATIENT_ID,
                NOT_FOUND
            ),
            Arguments.of(
                PATIENT_WITH_TEST_PROGRESS,
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

    @ParameterizedTest
    @MethodSource("getTestResultCases")
    fun getTestResultTest(patientId: Long, expectedHttpStatus: HttpStatus) {
        given()
            .`when`()
            .get("$baseUrl/$patientId/mental-test/result")
            .then()
            .assertThat()
            .status(expectedHttpStatus)
    }
}
