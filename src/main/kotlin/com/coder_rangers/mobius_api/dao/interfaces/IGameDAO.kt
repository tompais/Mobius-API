package com.coder_rangers.mobius_api.dao.interfaces

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category

interface IGameDAO {
    fun getMaxIdByCategory(category: Category): Long
    fun getMinIdByCategory(category: Category): Long
    fun getGameById(id: Long): Game?
    fun getGameByCategory(category: Category): Game
}
