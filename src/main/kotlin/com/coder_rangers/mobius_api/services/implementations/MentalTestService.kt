package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.enums.TestStatus.FINISHED
import com.coder_rangers.mobius_api.error.exceptions.FinishedTestException
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.categories.GameAnswersRequest
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
    override fun getMentalTestGame(patient: Patient, gameCategory: Category, test: Boolean): Game {
        assertThatTestIsNotFinished(patient)

        return getRandomGameByCategory(gameCategory, test)
    }

    override fun processTestGameAnswers(patient: Patient, testGameAnswersRequest: GameAnswersRequest<*>) {
        assertThatTestIsNotFinished(patient)

        gameService.processGameAnswers(patient, testGameAnswersRequest)
    }

    private fun getRandomGameByCategory(gameCategory: Category, test: Boolean): Game =
        gameService.getRandomGameByCategory(gameCategory, test)

    private fun assertThatTestIsNotFinished(patient: Patient) {
        if (patient.testStatus == FINISHED) {
            throw FinishedTestException(patient.id)
        }
    }
}
