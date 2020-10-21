package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.error.exceptions.FinishedTestException
import com.coder_rangers.mobius_api.models.Category
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
            Category.Type.ORIENTATION,
            Category.Type.FIXATION
        )
    }

    override fun getMentalTestGame(patient: Patient, nextCategoryType: Category.Type): Game {
        val testProgress = patient.testProgress
        if (testProgress?.status == FINISHED) {
            throw FinishedTestException(patient.id)
        }

        return getRandomOrMockGame(nextCategoryType)
    }

    private fun getRandomOrMockGame(nextCategoryType: Category.Type): Game {
        return if (isRandomGameCategory(nextCategoryType)) {
            gameService.getRandomGameByCategoryType(nextCategoryType)
        } else {
            gameService.getMockGame(nextCategoryType)
        }
    }

    private fun isRandomGameCategory(nextGameCategory: Category.Type) =
        nextGameCategory in RANDOM_GAME_CATEGORIES
}
