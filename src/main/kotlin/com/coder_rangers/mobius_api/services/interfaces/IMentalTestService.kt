package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.categories.GameAnswersRequest
import com.coder_rangers.mobius_api.responses.PatientTestResult

interface IMentalTestService {
    fun getMentalTestGame(patient: Patient, gameCategory: Category, test: Boolean): Game
    fun processTestGameAnswers(patient: Patient, testGameAnswersRequest: GameAnswersRequest<*>)
    fun getPatientTestResult(patientId: Long): PatientTestResult
}
