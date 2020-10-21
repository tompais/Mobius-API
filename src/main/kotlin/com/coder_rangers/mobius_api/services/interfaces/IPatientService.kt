package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Game

interface IPatientService {
    fun getMentalTestGame(id: Long, nextGameCategory: Game.Category): Game
}
