package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.components.interfaces.IGameAnswersResolver
import com.coder_rangers.mobius_api.enums.TestStatus.FINISHED
import com.coder_rangers.mobius_api.error.exceptions.FinishedTestException
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Game.Category.FIXATION
import com.coder_rangers.mobius_api.models.Game.Category.ORIENTATION
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.categories.FixationTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.OrientationTestGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TestGameAnswersRequest
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

    @Qualifier("orientationGameAnswersResolver")
    private val orientationGameAnswersResolver: IGameAnswersResolver<Boolean>,

    @Qualifier("fixationGameAnswersResolver")
    private val fixationGameAnswersResolver: IGameAnswersResolver<String>
) : IMentalTestService {
    private companion object {
        // TODO: We should put all the random categories inside.
        private val RANDOM_GAME_CATEGORIES = setOf(
            ORIENTATION,
            FIXATION
        )
    }

    override fun getMentalTestGame(patient: Patient, nextCategoryType: Category): Game {
        if (patient.testStatus == FINISHED) {
            throw FinishedTestException(patient.id)
        }

        return getRandomOrMockGame(nextCategoryType)
    }

    override fun processGameAnswers(patient: Patient, testGameAnswersRequest: TestGameAnswersRequest<*>) {
        val game = gameService.getGameById(testGameAnswersRequest.gameId)

        when (testGameAnswersRequest.category) {
            ORIENTATION -> orientationGameAnswersResolver.resolveAnswers(
                patient,
                game,
                (testGameAnswersRequest as OrientationTestGameAnswersRequest).patientTaskAnswersList
            )
            else -> fixationGameAnswersResolver.resolveAnswers(
                patient,
                game,
                (testGameAnswersRequest as FixationTestGameAnswersRequest).patientTaskAnswersList
            )
        }
    }

    private fun getRandomOrMockGame(nextGameCategory: Category): Game {
        return if (isRandomGameCategory(nextGameCategory)) {
            gameService.getRandomGameByCategory(nextGameCategory)
        } else {
            gameService.getMockGame(nextGameCategory)
        }
    }

    private fun isRandomGameCategory(nextGameCategory: Category) =
        nextGameCategory in RANDOM_GAME_CATEGORIES
}
