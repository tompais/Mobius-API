package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.categories.TestGameAnswersRequest
import com.coder_rangers.mobius_api.responses.PatientTestResult
import com.coder_rangers.mobius_api.view.models.HomeViewModel

interface IPatientService {
    fun getMentalTestGame(id: Long, nextGameCategory: Game.Category): Game
    fun processTestGameAnswers(id: Long, testGameAnswersRequest: TestGameAnswersRequest<*>)
    fun cleanTestProgress(id: Long)
    fun getTestResult(id: Long): PatientTestResult
    fun getHome(id: Long): HomeViewModel
    fun getActivePatientByEmailAndPassword(email: String, password: String): Patient
    fun createOrUpdatePatient(patient: Patient): Patient
    fun getActivePatientByEmail(email: String): Patient?
}
