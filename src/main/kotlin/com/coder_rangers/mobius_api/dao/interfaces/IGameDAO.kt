package com.coder_rangers.mobius_api.dao.interfaces

import com.coder_rangers.mobius_api.models.Category
import com.coder_rangers.mobius_api.models.Game

interface IGameDAO {
    fun getMaxIdByCategoryType(categoryType: Category.Type): Long
    fun getMinIdByCategoryType(categoryType: Category.Type): Long
    fun findGameById(id: Long): Game?
}
