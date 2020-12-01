package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.components.interfaces.IGameAnswersResolver
import com.coder_rangers.mobius_api.enums.TestStatus.FINISHED
import com.coder_rangers.mobius_api.error.exceptions.FinishedTestException
import com.coder_rangers.mobius_api.models.AnswerWithResult
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Game.Category.ATTENTION
import com.coder_rangers.mobius_api.models.Game.Category.CALCULATION
import com.coder_rangers.mobius_api.models.Game.Category.COMPREHENSION
import com.coder_rangers.mobius_api.models.Game.Category.DRAWING
import com.coder_rangers.mobius_api.models.Game.Category.MEMORY
import com.coder_rangers.mobius_api.models.Game.Category.ORIENTATION
import com.coder_rangers.mobius_api.models.Game.Category.READING
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.categories.AttentionTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.NumericTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TextTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TextTestGameAnswersWithResultsRequest
import com.coder_rangers.mobius_api.responses.PatientTestResult
import com.coder_rangers.mobius_api.services.interfaces.IGameService
import com.coder_rangers.mobius_api.services.interfaces.IMentalTestService
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MentalTestService @Autowired constructor(
    private val gameService: IGameService,

    private val taskResultService: ITaskResultService,

    @Qualifier("textGameAnswersWithResultsResolver")
    private val textGameAnswersWithResultsResolver: IGameAnswersResolver<AnswerWithResult<String>>,

    @Qualifier("textGameAnswersResolver")
    private val textGameAnswersResolver: IGameAnswersResolver<String>,

    @Qualifier("numericGameAnswersResolver")
    private val numericGameAnswersResolver: IGameAnswersResolver<Int>,

    @Qualifier("attentionGameAnswersResolver")
    private val attentionGameAnswersResolver: IGameAnswersResolver<Char>,

    @Qualifier("comprehensionGameAnswersResolver")
    private val comprehensionGameAnswersResolver: IGameAnswersResolver<String>,

    @Qualifier("drawingGameAnswersResolver")
    private val drawingGameAnswersResolver: IGameAnswersResolver<String>
) : IMentalTestService {
    override fun getMentalTestGame(patient: Patient, nextCategoryType: Category): Game {
        assertPatientIsNotFinished(patient)

        return getSpecificOrRandomGame(nextCategoryType)
    }

    override fun processGameAnswers(patient: Patient, testGameAnswersRequest: TestGameAnswersRequest<*>) {
        assertPatientIsNotFinished(patient)

        val game = gameService.getGameById(testGameAnswersRequest.gameId)

        when (testGameAnswersRequest.category) {
            ORIENTATION, MEMORY -> textGameAnswersWithResultsResolver.resolveAnswers(
                patient,
                game,
                (testGameAnswersRequest as TextTestGameAnswersWithResultsRequest).patientTaskAnswersRequestList
            )
            CALCULATION, READING -> numericGameAnswersResolver.resolveAnswers(
                patient,
                game,
                (testGameAnswersRequest as NumericTestGameAnswersRequest).patientTaskAnswersRequestList
            )
            ATTENTION -> attentionGameAnswersResolver.resolveAnswers(
                patient,
                game,
                (testGameAnswersRequest as AttentionTestGameAnswersRequest).patientTaskAnswersRequestList
            )
            COMPREHENSION -> comprehensionGameAnswersResolver.resolveAnswers(
                patient,
                game,
                (testGameAnswersRequest as TextTestGameAnswersRequest).patientTaskAnswersRequestList
            )
            DRAWING -> drawingGameAnswersResolver.resolveAnswers(
                patient,
                game,
                (testGameAnswersRequest as TextTestGameAnswersRequest).patientTaskAnswersRequestList
            )
            else -> textGameAnswersResolver.resolveAnswers(
                patient,
                game,
                (testGameAnswersRequest as TextTestGameAnswersRequest).patientTaskAnswersRequestList
            )
        }
    }

    override fun getPatientTestResult(patientId: Long): PatientTestResult =
        taskResultService.getPatientTestResult(patientId)

    private fun getSpecificOrRandomGame(nextGameCategory: Category): Game =
        gameService.getRandomGameByCategory(nextGameCategory)

    private fun assertPatientIsNotFinished(patient: Patient) {
        if (patient.testStatus == FINISHED) {
            throw FinishedTestException(patient.id)
        }
    }
}
