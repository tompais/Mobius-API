package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.error.exceptions.FinishedTestException
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.TestProgress.Status.FINISHED
import com.coder_rangers.mobius_api.services.interfaces.IGameService
import com.coder_rangers.mobius_api.services.interfaces.IMentalTestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MentalTestService @Autowired constructor(
    private val gameService: IGameService
) : IMentalTestService {
    private companion object {
        // TODO: We should put all the random categories inside.
        private val RANDOM_GAME_CATEGORIES = setOf(
            Game.Category.ORIENTATION,
            Game.Category.FIXATION
        )
    }

    override fun getMentalTestGame(patient: Patient, nextCategoryType: Game.Category): Game {
        val testProgress = patient.testProgress
        if (testProgress?.status == FINISHED) {
            throw FinishedTestException(patient.id)
        }

        return getRandomOrMockGame(nextCategoryType)
    }

    private fun getRandomOrMockGame(nextGameCategory: Game.Category): Game {
        return if (isRandomGameCategory(nextGameCategory)) {
            gameService.getRandomGameByCategory(nextGameCategory)
        } else {
            gameService.getMockGame(nextGameCategory)
        }
    }

    private fun isRandomGameCategory(nextGameCategory: Game.Category) =
        nextGameCategory in RANDOM_GAME_CATEGORIES
}
