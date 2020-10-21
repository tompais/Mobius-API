package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Category
import com.coder_rangers.mobius_api.models.Game

interface IPatientService {
    fun getMentalTestGame(id: Long, nextCategoryType: Category.Type): Game
}
