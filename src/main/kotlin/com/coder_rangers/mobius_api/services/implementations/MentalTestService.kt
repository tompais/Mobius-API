package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.error.exceptions.FinishedTestException
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Game.Category.ATTENTION
import com.coder_rangers.mobius_api.models.Game.Category.CALCULATION
import com.coder_rangers.mobius_api.models.Game.Category.FIXATION
import com.coder_rangers.mobius_api.models.Game.Category.LANGUAGE_AND_PRAXIS
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
        private val RANDOM_GAME_CATEGORIES = setOf(
            FIXATION,
            ATTENTION,
            CALCULATION,
            LANGUAGE_AND_PRAXIS // TODO dividir lenguaje y praxia en varias categories
        )
    }

    override fun getMentalTestGame(patient: Patient, nextCategoryType: Category): Game {
        val testProgress = patient.testProgress
        if (testProgress?.status == FINISHED) {
            throw FinishedTestException(patient.id)
        }

        return getSpecificOrRandomGame(nextCategoryType)
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
