package com.coder_rangers.mobius_api.dao.interfaces

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.Task

interface ITaskResultDAO {
    fun createTaskResult(patient: Patient, task: Task, score: Int, patientAnswers: List<Answer>): Task.Result
    fun getPatientResults(patientId: Long, gameCategories: List<Category>): List<Task.Result>
}
