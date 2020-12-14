package com.coder_rangers.mobius_api.dao.interfaces

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category

interface IGameDAO {
    fun getIdsByCategory(category: Category, test: Boolean): List<Long>
    fun getGameById(id: Long): Game?
    fun getNotTestCategories(): List<Category>
}
