package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.ITaskResultDAO
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.PatientAnswer
import com.coder_rangers.mobius_api.models.Task
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TaskResultService @Autowired constructor(
    private val taskResultDAO: ITaskResultDAO
) : ITaskResultService {
    override fun createTaskResult(patient: Patient, task: Task, score: Int, patientAnswers: List<PatientAnswer>): Task.Result =
        taskResultDAO.createTaskResult(patient, task, score, patientAnswers)
}
