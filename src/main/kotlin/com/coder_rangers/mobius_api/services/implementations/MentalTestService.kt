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
import com.coder_rangers.mobius_api.models.Game.Category.FIXATION
import com.coder_rangers.mobius_api.models.Game.Category.MEMORY
import com.coder_rangers.mobius_api.models.Game.Category.ORIENTATION
import com.coder_rangers.mobius_api.models.Game.Category.VISUALIZATION
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.categories.AttentionTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.CalculationTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TextTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TextTestGameAnswersWithResultsRequest
import com.coder_rangers.mobius_api.services.interfaces.IGameService
import com.coder_rangers.mobius_api.services.interfaces.IMentalTestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MentalTestService @Autowired constructor(
    private val gameService: IGameService,

    @Qualifier("textGameAnswersWithResultsResolver")
    private val textGameAnswersWithResultsResolver: IGameAnswersResolver<AnswerWithResult<String>>,

    @Qualifier("textGameAnswersResolver")
    private val textGameAnswersResolver: IGameAnswersResolver<String>,

    @Qualifier("calculationGameAnswersResolver")
    private val calculationGameAnswersResolver: IGameAnswersResolver<Int>,

    @Qualifier("attentionGameAnswersResolver")
    private val attentionGameAnswersResolver: IGameAnswersResolver<Char>,

    @Qualifier("comprehensionGameAnswersResolver")
    private val comprehensionGameAnswersResolver: IGameAnswersResolver<String>
) : IMentalTestService {
    private companion object {
        private val RANDOM_GAME_CATEGORIES = setOf(
            FIXATION,
            ATTENTION,
            CALCULATION,
            ATTENTION,
            VISUALIZATION,
            COMPREHENSION
        )
    }

    override fun getMentalTestGame(patient: Patient, nextCategoryType: Category): Game {
        if (patient.testStatus == FINISHED) {
            throw FinishedTestException(patient.id)
        }

        return getSpecificOrRandomGame(nextCategoryType)
    }

    override fun processGameAnswers(patient: Patient, testGameAnswersRequest: TestGameAnswersRequest<*>) {
        val game = gameService.getGameById(testGameAnswersRequest.gameId)

        when (testGameAnswersRequest.category) {
            ORIENTATION, MEMORY -> textGameAnswersWithResultsResolver.resolveAnswers(
                patient,
                game,
                (testGameAnswersRequest as TextTestGameAnswersWithResultsRequest).patientTaskAnswersRequestList
            )
            CALCULATION -> calculationGameAnswersResolver.resolveAnswers(
                patient,
                game,
                (testGameAnswersRequest as CalculationTestGameAnswersRequest).patientTaskAnswersRequestList
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
            else -> textGameAnswersResolver.resolveAnswers(
                patient,
                game,
                (testGameAnswersRequest as TextTestGameAnswersRequest).patientTaskAnswersRequestList
            )
        }
    }

    private fun getSpecificOrRandomGame(nextGameCategory: Category): Game {
        return if (isRandomGameCategory(nextGameCategory)) {
            gameService.getRandomGameByCategory(nextGameCategory)
        } else {
            gameService.getSpecificGameByCategory(nextGameCategory)
        }
    }

    private fun isRandomGameCategory(nextGameCategory: Category) =
        nextGameCategory in RANDOM_GAME_CATEGORIES
}
