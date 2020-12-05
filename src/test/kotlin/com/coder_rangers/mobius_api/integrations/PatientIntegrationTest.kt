package com.coder_rangers.mobius_api.integrations

import com.amazonaws.services.s3.AmazonS3
import com.coder_rangers.mobius_api.database.repositories.IPatientRepository
import com.coder_rangers.mobius_api.database.repositories.ITaskResultRepository
import com.coder_rangers.mobius_api.enums.TestStatus.IN_PROGRESS
import com.coder_rangers.mobius_api.models.AnswerWithResult
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Game.Category.ATTENTION
import com.coder_rangers.mobius_api.models.Game.Category.CALCULATION
import com.coder_rangers.mobius_api.models.Game.Category.COMPREHENSION
import com.coder_rangers.mobius_api.models.Game.Category.DRAWING
import com.coder_rangers.mobius_api.models.Game.Category.FIXATION
import com.coder_rangers.mobius_api.models.Game.Category.MEMORY
import com.coder_rangers.mobius_api.models.Game.Category.ORIENTATION
import com.coder_rangers.mobius_api.models.Game.Category.READING
import com.coder_rangers.mobius_api.models.Game.Category.REPETITION
import com.coder_rangers.mobius_api.models.Game.Category.VISUALIZATION
import com.coder_rangers.mobius_api.models.Game.Category.WRITING
import com.coder_rangers.mobius_api.notifications.redis.messages.UploadFileMessage
import com.coder_rangers.mobius_api.notifications.redis.publishers.MessagePublisher
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.AttentionGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.GameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.NumericGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TextGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TextGameAnswersWithResultsRequest
import com.coder_rangers.mobius_api.utils.MockUtils.getImageFromClasspathInBase64
import com.coder_rangers.mobius_api.utils.TestConstant.NON_EXISTENT_PATIENT_ID
import com.coder_rangers.mobius_api.utils.TestConstant.PATIENT_ID
import com.coder_rangers.mobius_api.utils.TestConstant.PATIENT_ID_WITH_FINISHED_TEST
import com.coder_rangers.mobius_api.utils.TestConstant.PATIENT_WITHOUT_TEST_PROGRESS
import com.coder_rangers.mobius_api.utils.TestConstant.PATIENT_WITH_TEST_PROGRESS
import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.SpykBean
import io.mockk.clearMocks
import io.mockk.clearStaticMockk
import io.mockk.every
import io.mockk.mockk
import io.restassured.http.ContentType.JSON
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import javax.imageio.ImageIO

class PatientIntegrationTest @Autowired constructor(
    private val patientRepository: IPatientRepository,

    @Qualifier("uploadFileToS3Subscriber")
    private val uploadFileToS3Subscriber: MessageListener
) : BaseIntegrationTest("/patients") {
    @SpykBean
    private lateinit var taskResultRepository: ITaskResultRepository

    @MockkBean(name = "uploadFileToS3Publisher", relaxed = true)
    private lateinit var uploadFileToS3Publisher: MessagePublisher

    @MockkBean(relaxed = true)
    @Suppress("UNUSED")
    private lateinit var amazonS3Client: AmazonS3

    companion object {
        @JvmStatic
        @Suppress("UNUSED")
        fun getGameCases() = listOf(
            Arguments.of(
                ORIENTATION,
                PATIENT_WITHOUT_TEST_PROGRESS,
                true,
                OK
            ),
            Arguments.of(
                ORIENTATION,
                NON_EXISTENT_PATIENT_ID,
                true,
                NOT_FOUND
            ),
            Arguments.of(
                ORIENTATION,
                PATIENT_ID_WITH_FINISHED_TEST,
                false,
                BAD_REQUEST
            ),
            Arguments.of(
                FIXATION,
                PATIENT_WITH_TEST_PROGRESS,
                false,
                BAD_REQUEST
            ),
            Arguments.of(
                ORIENTATION,
                PATIENT_ID_WITH_FINISHED_TEST,
                false,
                BAD_REQUEST
            )
        )

        @JvmStatic
        @Suppress("UNUSED")
        fun processGameAnswersCases() = listOf(
            Arguments.of(
                PATIENT_WITHOUT_TEST_PROGRESS,
                TextGameAnswersWithResultsRequest(
                    category = ORIENTATION,
                    gameId = 1,
                    areTestGameAnswers = true,
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
                TextGameAnswersWithResultsRequest(
                    category = ORIENTATION,
                    gameId = 1,
                    areTestGameAnswers = true,
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
                TextGameAnswersRequest(
                    category = FIXATION,
                    gameId = 2,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 9, listOf("Bicicleta", "Cuchara", "Manzana")),
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                TextGameAnswersRequest(
                    category = FIXATION,
                    gameId = 2,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 9, listOf("Bicicleta", "Cuchara", "Manzana")),
                        PatientTaskAnswersRequest(taskId = 1, listOf("Bicicleta", "Cuchara", "Manzana")),
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                TextGameAnswersRequest(
                    category = FIXATION,
                    gameId = 2,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 1, listOf("bicicleta", "Cuchara", "Manzana")),
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_WITHOUT_TEST_PROGRESS,
                TextGameAnswersRequest(
                    category = FIXATION,
                    gameId = 2,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 9, listOf("Bicicleta", "Cuchara", "Coca")),
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                NumericGameAnswersRequest(
                    category = CALCULATION,
                    gameId = 3,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 10, listOf(93, 86, 79, 72, 65))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                NumericGameAnswersRequest(
                    category = CALCULATION,
                    gameId = 3,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 10, listOf(93)),
                        PatientTaskAnswersRequest(taskId = 1, listOf(86, 74))
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                AttentionGameAnswersRequest(
                    category = ATTENTION,
                    gameId = 4,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 11, listOf('o', 'd', 'n', 'u', 'm'))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                AttentionGameAnswersRequest(
                    category = ATTENTION,
                    gameId = 4,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 1, listOf('b', 'a', 'M')),
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                TextGameAnswersWithResultsRequest(
                    category = MEMORY,
                    gameId = 5,
                    areTestGameAnswers = true,
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
                TextGameAnswersRequest(
                    category = VISUALIZATION,
                    gameId = 6,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 13, listOf("Tigre"))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                TextGameAnswersRequest(
                    category = VISUALIZATION,
                    gameId = 6,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 9, listOf("Manzana")),
                        PatientTaskAnswersRequest(taskId = 1, listOf("Bicicleta"))
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                TextGameAnswersRequest(
                    category = REPETITION,
                    gameId = 7,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 14, listOf("El flan tiene frutillas y frambuesas"))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                TextGameAnswersRequest(
                    category = COMPREHENSION,
                    gameId = 8,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 15, listOf("triangulo", "cuadrado", "circulo"))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                NumericGameAnswersRequest(
                    category = READING,
                    gameId = 9,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 16, listOf(4))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                TextGameAnswersRequest(
                    category = WRITING,
                    gameId = 10,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(taskId = 17, listOf("Si llueve mucho, entra agua por el tejado"))
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                TextGameAnswersRequest(
                    category = DRAWING,
                    gameId = 11,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(
                            taskId = 18,
                            listOf(
                                getImageFromClasspathInBase64("dibujo-chico.png")
                            )
                        )
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                TextGameAnswersRequest(
                    category = DRAWING,
                    gameId = 11,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(
                            taskId = 18,
                            listOf(
                                getImageFromClasspathInBase64("spiderman.jpg")
                            )
                        )
                    )
                ),
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID,
                TextGameAnswersRequest(
                    category = DRAWING,
                    gameId = 11,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(
                            taskId = 18,
                            listOf(
                                getImageFromClasspathInBase64("dibujo-menos70.png")
                            )
                        )
                    )
                ),
                NO_CONTENT
            ),
            Arguments.of(
                PATIENT_ID,
                TextGameAnswersRequest(
                    category = DRAWING,
                    gameId = 11,
                    areTestGameAnswers = true,
                    patientTaskAnswersRequestList = listOf(
                        PatientTaskAnswersRequest(
                            taskId = 18,
                            listOf(
                                getImageFromClasspathInBase64("dibujo.png")
                            )
                        )
                    )
                ),
                NO_CONTENT
            )
        )

        @JvmStatic
        @Suppress("UNUSED")
        fun getTestResultCases() = listOf(
            Arguments.of(
                NON_EXISTENT_PATIENT_ID,
                NOT_FOUND
            ),
            Arguments.of(
                PATIENT_WITH_TEST_PROGRESS,
                BAD_REQUEST
            ),
            Arguments.of(
                PATIENT_ID_WITH_FINISHED_TEST,
                OK
            )
        )

        @JvmStatic
        @Suppress("UNUSED")
        fun getHomeCases() = listOf(
            Arguments.of(
                NON_EXISTENT_PATIENT_ID,
                NOT_FOUND
            ),
            Arguments.of(
                PATIENT_ID,
                OK
            )
        )
    }

    @BeforeEach
    fun beforeEach() {
        clearMocks(taskResultRepository)
        clearStaticMockk(ImageIO::class)
    }

    @ParameterizedTest
    @MethodSource("getGameCases")
    fun getGameTest(nextGameCategory: Category, patientId: Long, isTestGame: Boolean, expectedHttpStatus: HttpStatus) {
        given()
            .queryParam("next-game-category", nextGameCategory)
            .queryParam("is-test-game", isTestGame)
            .`when`()
            .get("$baseUrl/$patientId/game")
            .then()
            .assertThat()
            .status(expectedHttpStatus)
    }

    @ParameterizedTest
    @MethodSource("processGameAnswersCases")
    fun processGameAnswersTest(
        id: Long,
        testGameAnswersRequest: GameAnswersRequest<*>,
        expectedHttpStatus: HttpStatus
    ) {
        patientRepository.findByIdOrNull(id)?.let {
            it.testStatus = IN_PROGRESS
            patientRepository.saveAndFlush(it)
        }

        if (testGameAnswersRequest.category == DRAWING) {
            every { uploadFileToS3Publisher.publish(any()) } answers {
                val message = mockk<Message>()

                every { message.toString() } returns mapper.writeValueAsString(
                    mockk<UploadFileMessage>(relaxed = true)
                )

                uploadFileToS3Subscriber.onMessage(
                    message,
                    null
                )
            }
        }

        given()
            .accept(JSON)
            .contentType(JSON)
            .body(
                mapper.writeValueAsBytes(testGameAnswersRequest)
            )
            .`when`()
            .post("$baseUrl/$id/game/answers")
            .then()
            .assertThat()
            .status(expectedHttpStatus)
    }

    @ParameterizedTest
    @MethodSource("getTestResultCases")
    fun getTestResultTest(patientId: Long, expectedHttpStatus: HttpStatus) {
        if (expectedHttpStatus == OK) {
            every { taskResultRepository.getTestTotalScore(patientId) } returns 27
        }
        given()
            .`when`()
            .get("$baseUrl/$patientId/mental-test/result")
            .then()
            .assertThat()
            .status(expectedHttpStatus)
    }

    @ParameterizedTest
    @MethodSource("getHomeCases")
    fun getHomeTest(patientId: Long, expectedHttpStatus: HttpStatus) {
        given()
            .`when`()
            .get("$baseUrl/$patientId/home")
            .then()
            .assertThat()
            .status(expectedHttpStatus)
    }
}
