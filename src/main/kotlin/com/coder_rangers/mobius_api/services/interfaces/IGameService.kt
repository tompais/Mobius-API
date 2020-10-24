package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category

interface IGameService {
    fun getRandomGameByCategory(category: Game.Category): Game
    fun getSpecificGameByCategory(category: Game.Category): Game
    fun getGameById(id: Long): Game
}
