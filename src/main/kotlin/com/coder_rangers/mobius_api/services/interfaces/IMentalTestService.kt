package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.categories.TestGameAnswersRequest

interface IMentalTestService {
    fun getMentalTestGame(patient: Patient, nextCategoryType: Game.Category): Game
    fun processGameAnswers(patient: Patient, testGameAnswersRequest: TestGameAnswersRequest<*>)
}
