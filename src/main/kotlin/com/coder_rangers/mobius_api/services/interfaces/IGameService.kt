package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.categories.GameAnswersRequest

interface IGameService {
    fun getRandomGameByCategory(category: Category, test: Boolean): Game
    fun getGameById(id: Long): Game
    fun getNotTestCategories(): List<Category>
    fun getNotTestGame(patient: Patient, gameCategory: Category, test: Boolean): Game
    fun processGameAnswers(patient: Patient, gameAnswersRequest: GameAnswersRequest<*>)
}
