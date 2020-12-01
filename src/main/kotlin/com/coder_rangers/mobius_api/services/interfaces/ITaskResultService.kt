package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.PatientAnswer
import com.coder_rangers.mobius_api.models.Task
import com.coder_rangers.mobius_api.responses.PatientTestResult

interface ITaskResultService {
    fun createTaskResult(patient: Patient, task: Task, score: Int, patientAnswers: List<PatientAnswer>): Task.Result
    fun getPatientTestResult(patientId: Long): PatientTestResult
    fun getRecommendedCategory(patientId: Long, gameCategories: List<Category>): Category
}
