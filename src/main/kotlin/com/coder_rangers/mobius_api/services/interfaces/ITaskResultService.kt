package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.Task

interface ITaskResultService {
    fun createTaskResult(patient: Patient, task: Task, score: Int, patientAnswers: List<Answer>): Task.Result
    fun getRecommendedCategory(patientId: Long, gameCategories: List<Category>): Category
}
