package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IPatientDAO
import com.coder_rangers.mobius_api.dto.AnswerDTO
import com.coder_rangers.mobius_api.dto.AnswerDTO.Type.IMAGE
import com.coder_rangers.mobius_api.dto.AnswerDTO.Type.TEXT
import com.coder_rangers.mobius_api.dto.TaskResultDTO
import com.coder_rangers.mobius_api.enums.DementiaLevel
import com.coder_rangers.mobius_api.enums.DementiaLevel.MILD_TO_MODERATE_DEMENTIA
import com.coder_rangers.mobius_api.enums.DementiaLevel.MODERATE_TO_SEVERE_DEMENTIA
import com.coder_rangers.mobius_api.enums.DementiaLevel.NO_DEMENTIA
import com.coder_rangers.mobius_api.enums.DementiaLevel.POSSIBLE_DEMENTIA
import com.coder_rangers.mobius_api.enums.DementiaLevel.SEVERE_DEMENTIA
import com.coder_rangers.mobius_api.enums.TestStatus.FINISHED
import com.coder_rangers.mobius_api.enums.TestStatus.IN_PROGRESS
import com.coder_rangers.mobius_api.error.exceptions.DuplicatedPatientException
import com.coder_rangers.mobius_api.error.exceptions.PatientNotFoundException
import com.coder_rangers.mobius_api.error.exceptions.PossibleAnswerParseException
import com.coder_rangers.mobius_api.error.exceptions.TestNotFinishedException
import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.Answer.Type.EXPECTED
import com.coder_rangers.mobius_api.models.Answer.Type.PATIENT
import com.coder_rangers.mobius_api.models.CharAnswer
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Game.Category.DRAWING
import com.coder_rangers.mobius_api.models.ImageAnswer
import com.coder_rangers.mobius_api.models.NumericAnswer
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.Task
import com.coder_rangers.mobius_api.models.TextAnswer
import com.coder_rangers.mobius_api.notifications.redis.messages.TestFinishedMessage
import com.coder_rangers.mobius_api.notifications.redis.publishers.MessagePublisher
import com.coder_rangers.mobius_api.requests.categories.GameAnswersRequest
import com.coder_rangers.mobius_api.responses.CompleteTestResult
import com.coder_rangers.mobius_api.responses.GameResult
import com.coder_rangers.mobius_api.responses.GeneralTestResult
import com.coder_rangers.mobius_api.responses.TestResult
import com.coder_rangers.mobius_api.responses.TestResult.Type
import com.coder_rangers.mobius_api.responses.TestResult.Type.GENERAL
import com.coder_rangers.mobius_api.services.interfaces.IGameService
import com.coder_rangers.mobius_api.services.interfaces.IImageService
import com.coder_rangers.mobius_api.services.interfaces.IMentalTestService
import com.coder_rangers.mobius_api.services.interfaces.IPatientService
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import com.coder_rangers.mobius_api.view.models.HomeViewModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.ResourceUtils
import org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX
import java.util.Base64

@Service
@Transactional
class PatientService @Autowired constructor(
    private val patientDAO: IPatientDAO,
    private val mentalTestService: IMentalTestService,
    private val gameService: IGameService,
    private val taskResultService: ITaskResultService,

    @Qualifier("testFinishedPublisher")
    private val testFinishedPublisher: MessagePublisher<TestFinishedMessage>,

    private val imageService: IImageService
) : IPatientService {
    override fun getGame(id: Long, gameCategory: Category, test: Boolean): Game {
        val patient = getActivePatientById(id)

        return if (test) {
            mentalTestService.getMentalTestGame(patient, gameCategory, test)
        } else {
            assertThatTestIsFinished(patient)
            gameService.getNotTestGame(patient, gameCategory, test)
        }
    }

    override fun processGameAnswers(id: Long, gameAnswersRequest: GameAnswersRequest<*>) {
        val patient = getActivePatientById(id)

        if (gameAnswersRequest.areTestGameAnswers) {
            mentalTestService.processTestGameAnswers(patient, gameAnswersRequest)
            checkAndProcessIfTestFinished(patient, gameAnswersRequest.category)
        } else {
            assertThatTestIsFinished(patient)
            gameService.processGameAnswers(patient, gameAnswersRequest)
        }
    }

    private fun assertThatTestIsFinished(patient: Patient) {
        if (patient.testStatus != FINISHED)
            throw TestNotFinishedException(patient.id)
    }

    override fun getTestResult(id: Long, testResultType: Type): TestResult =
        getActivePatientById(id).let { patient ->
            assertThatTestIsFinished(patient)

            val testTaskResults = patient.getTestTaskResults()
            val testTotalScore = testTaskResults.getTestTotalScore()
            val dementiaLevel = getDementiaLevel(testTotalScore)

            if (testResultType == GENERAL) {
                buildGeneralTestResult(testTotalScore, dementiaLevel)
            } else {
                buildCompleteTestResult(testTotalScore, dementiaLevel, patient, testTaskResults)
            }
        }

    private fun buildCompleteTestResult(
        testTotalScore: Int,
        dementiaLevel: DementiaLevel,
        patient: Patient,
        testTaskResults: List<Task.Result>
    ): TestResult = CompleteTestResult(
        score = testTotalScore,
        dementiaLevel = dementiaLevel,
        patientFirstName = patient.firstName,
        patientLastName = patient.lastName,
        testPlayedDate = testTaskResults.maxByOrNull { it.playedDate }!!.playedDate,
        gameResults = testTaskResults.groupBy { taskResult ->
            taskResult.task.game!!.category
        }.toSortedMap().map { taskResultsByCategory ->
            GameResult(
                category = taskResultsByCategory.key,
                taskResults = taskResultsByCategory.value.map { taskResult ->
                    TaskResultDTO(
                        description = taskResult.task.description,
                        patientAnswers = taskResult.patientAnswers.mapToAnswerDTOList(),
                        expectedAnswers = taskResult.task.answers!!.mapToAnswerDTOList(),
                        score = taskResult.score
                    )
                }
            )
        }
    )

    private fun Collection<Answer>.mapToAnswerDTOList(): List<AnswerDTO> =
        sortedBy { answer -> answer.id }.map { answer ->
            AnswerDTO(
                type = if (answer is ImageAnswer) IMAGE else TEXT,
                value = when (answer) {
                    is CharAnswer -> answer.letter.toString()
                    is NumericAnswer -> answer.number.toString()
                    is TextAnswer -> answer.text
                    else -> (answer as ImageAnswer).let { imageAnswer ->
                        when (imageAnswer.type) {
                            PATIENT -> imageService.getImageFromS3(imageAnswer.imageName)
                            EXPECTED ->
                                ResourceUtils.getURL("${CLASSPATH_URL_PREFIX}static/images/${imageAnswer.imageName}")
                                    .readBytes()
                            else -> throw PossibleAnswerParseException()
                        }.let { byteArray ->
                            Base64.getEncoder().encodeToString(byteArray)
                        }
                    }
                }
            )
        }

    private fun buildGeneralTestResult(testTotalScore: Int, dementiaLevel: DementiaLevel): TestResult =
        GeneralTestResult(testTotalScore, dementiaLevel)

    private fun getDementiaLevel(testTotalScore: Int): DementiaLevel = when (testTotalScore) {
        in 27..32 -> NO_DEMENTIA
        in 22..26 -> POSSIBLE_DEMENTIA
        in 17..21 -> MILD_TO_MODERATE_DEMENTIA
        in 12..16 -> MODERATE_TO_SEVERE_DEMENTIA
        else -> SEVERE_DEMENTIA
    }

    private fun List<Task.Result>.getTestTotalScore(): Int = sumBy { taskResult ->
        taskResult.score
    }

    override fun getHome(id: Long): HomeViewModel {
        getActivePatientById(id)

        val categories = gameService.getNotTestCategories()

        val recommendedCategory = taskResultService.getRecommendedCategory(id, categories)

        return HomeViewModel(recommendedCategory, categories.filterNot { it == recommendedCategory })
    }

    override fun getActivePatientByEmailAndPassword(email: String, password: String): Patient =
        patientDAO.getActivePatientByEmailAndPassword(email, password) ?: throw PatientNotFoundException()

    override fun createOrUpdatePatient(patient: Patient): Patient = try {
        patientDAO.createOrUpdatePatient(patient)
    } catch (dive: DataIntegrityViolationException) {
        throw DuplicatedPatientException(patient.email, dive)
    }

    override fun getActivePatientByEmail(email: String): Patient? = patientDAO.getActivePatientByEmail(email)

    override fun cleanTestProgress(id: Long) {
        getActivePatientById(id).also { patient ->
            patient.testStatus = IN_PROGRESS
            patient.taskResults?.forEach {
                it.patient = null
            }
            patient.taskResults?.clear()
            patientDAO.createOrUpdatePatient(patient)
        }
    }

    override fun getActivePatientById(id: Long): Patient =
        patientDAO.findActivePatientById(id) ?: throw PatientNotFoundException(id)

    override fun getActivePatientsWithFinishedTest(): Set<Patient> =
        patientDAO.getActivePatientsWithFinishedTest()

    private fun isLastTestCategory(category: Category) = category == DRAWING

    private fun checkAndProcessIfTestFinished(patient: Patient, category: Category) {
        if (isLastTestCategory(category)) {
            updateTestStatus(patient).also {
                sendResultsToGuardians(patient)
            }
        }
    }

    private fun sendResultsToGuardians(patient: Patient) {
        testFinishedPublisher.publish(
            TestFinishedMessage(
                patientId = patient.id,
                guardianEmails = patient.guardians.map { it.email }.toSet()
            )
        )
    }

    private fun updateTestStatus(patient: Patient) {
        patient.testStatus = FINISHED
        patientDAO.createOrUpdatePatient(patient)
    }
}
