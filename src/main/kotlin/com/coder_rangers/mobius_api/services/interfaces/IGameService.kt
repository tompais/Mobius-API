package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Category
import com.coder_rangers.mobius_api.models.Game

interface IGameService {
    fun getRandomGameByCategoryType(categoryType: Category.Type): Game
    fun getMockGame(nextGameCategory: Category.Type): Game
}
