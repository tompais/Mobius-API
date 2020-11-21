package com.coder_rangers.mobius_api.dao.interfaces

import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.PatientAnswer
import com.coder_rangers.mobius_api.models.Task

interface ITaskResultDAO {
    fun createTaskResult(patient: Patient, task: Task, score: Int, patientAnswers: List<PatientAnswer>): Task.Result
    fun getPatientTestResult(patientId: Long): Int
}
