package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Category
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Patient

interface IMentalTestService {
    fun getMentalTestGame(patient: Patient, nextCategoryType: Category.Type): Game
}
