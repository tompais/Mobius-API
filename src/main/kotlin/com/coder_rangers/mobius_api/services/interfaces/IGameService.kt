package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Game

interface IGameService {
    fun getRandomGameByCategory(category: Game.Category): Game
    fun getMockGame(category: Game.Category): Game
    fun getGameById(id: Long): Game
}
