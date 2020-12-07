package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.categories.GameAnswersRequest

interface IMentalTestService {
    fun getMentalTestGame(patient: Patient, gameCategory: Category, test: Boolean): Game
    fun processTestGameAnswers(patient: Patient, testGameAnswersRequest: GameAnswersRequest<*>)
}
