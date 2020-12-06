package com.coder_rangers.mobius_api.dao.interfaces

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category

interface IGameDAO {
    fun getMaxIdByCategory(category: Category, test: Boolean): Long
    fun getMinIdByCategory(category: Category, test: Boolean): Long
    fun getGameById(id: Long): Game?
    fun getNotTestCategories(): List<Category>
}
