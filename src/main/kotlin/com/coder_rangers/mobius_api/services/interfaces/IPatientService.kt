package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.categories.GameAnswersRequest

interface IPatientService {
    fun getMentalTestGame(id: Long, nextGameCategory: Game.Category): Game
    fun processGameAnswers(id: Long, gameAnswersRequest: GameAnswersRequest)
}
