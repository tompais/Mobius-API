package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.Task

interface ITaskResultService {
    fun createTaskResult(patient: Patient, task: Task, score: Int, isTest: Boolean): Task.Result
}
