package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category

interface IGameService {
    fun getRandomGameByCategory(category: Category): Game
    fun getSpecificGameByCategory(category: Category): Game
}
