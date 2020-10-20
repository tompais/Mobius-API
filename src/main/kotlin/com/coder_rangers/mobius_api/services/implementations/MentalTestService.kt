package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.error.exceptions.NoMoreMentalGamesException
import com.coder_rangers.mobius_api.models.Category
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.TestProgress
import com.coder_rangers.mobius_api.models.TestProgress.Status.IN_PROGRESS
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
        private val TEST_GAME_CATEGORIES = Category.Type.values().filter { it.isTestCategoryType }

        // TODO: We should put all the random categories inside.
        private val RANDOM_GAME_CATEGORIES = setOf(
            Category.Type.ORIENTATION,
            Category.Type.FIXATION
        )
    }

    override fun getMentalTestGame(patient: Patient): Game {
        val nextGameCategory = getNextGameCategory(patient.testProgress)

        return if (nextGameCategory != null) {
            getRandomOrMockGame(nextGameCategory)
        } else throw NoMoreMentalGamesException(patient.id)
    }

    private fun getRandomOrMockGame(nextGameCategory: Category.Type): Game {
        return if (isRandomGameCategory(nextGameCategory)) {
            gameService.getRandomGameByCategoryType(nextGameCategory)
        } else {
            gameService.getMockGame(nextGameCategory)
        }
    }

    private fun isRandomGameCategory(nextGameCategory: Category.Type) =
        nextGameCategory in RANDOM_GAME_CATEGORIES

    private fun getNextGameCategory(testProgress: TestProgress?): Category.Type? {
        return when {
            testProgress == null -> TEST_GAME_CATEGORIES.first()
            testProgress.status == IN_PROGRESS -> TEST_GAME_CATEGORIES.firstOrNull {
                it.ordinal == testProgress.lastCategoryPlayed.type.ordinal + 1
            }
            else -> null
        }
    }
}
