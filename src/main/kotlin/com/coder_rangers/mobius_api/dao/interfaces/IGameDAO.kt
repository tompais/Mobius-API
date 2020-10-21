package com.coder_rangers.mobius_api.dao.interfaces

import com.coder_rangers.mobius_api.models.Game

interface IGameDAO {
    fun getMaxIdByCategory(category: Game.Category): Long
    fun getMinIdByCategory(category: Game.Category): Long
    fun findGameById(id: Long): Game?
}
