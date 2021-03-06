package com.coder_rangers.mobius_api.dao.implementations

import com.coder_rangers.mobius_api.dao.interfaces.ITaskResultDAO
import com.coder_rangers.mobius_api.database.repositories.ITaskResultRepository
import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class TaskResultDAO @Autowired constructor(
    private val taskResultRepository: ITaskResultRepository
) : ITaskResultDAO {
    override fun createTaskResult(
        patient: Patient,
        task: Task,
        score: Int,
        patientAnswers: List<Answer>
    ): Task.Result =
        taskResultRepository.saveAndFlush(
            Task.Result(
                id = 0,
                patient = patient,
                task = task,
                score = score
            ).also { taskResult ->
                taskResult.patientAnswers.addAll(
                    patientAnswers.map { patientAnswer ->
                        patientAnswer.apply {
                            this.taskResult = taskResult
                        }
                    }
                )
            }
        )

    override fun getPatientResults(
        patientId: Long,
        gameCategories: List<Category>
    ): List<Task.Result> =
        taskResultRepository.getPatientResults(patientId, gameCategories)
}
