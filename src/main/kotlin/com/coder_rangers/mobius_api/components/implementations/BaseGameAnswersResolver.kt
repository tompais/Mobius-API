package com.coder_rangers.mobius_api.components.implementations

import com.coder_rangers.mobius_api.components.interfaces.IGameAnswersResolver
import com.coder_rangers.mobius_api.error.exceptions.NotAllTasksProvidedException
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService

abstract class BaseGameAnswersResolver<T>(
    private val taskResultService: ITaskResultService
) : IGameAnswersResolver<T> {
    override fun resolveAnswers(
        patient: Patient,
        game: Game,
        patientTaskAnswersRequestList: List<PatientTaskAnswersRequest<T>>
    ) {
        checkTasks(game, patientTaskAnswersRequestList)
        game.tasks.forEach { task ->
            val patientTaskAnswersRequest = patientTaskAnswersRequestList.first { it.taskId == task.id }
            val score = getScore(patientTaskAnswersRequest, task.answers)
            val patientAnswers = transformToPatientAnswers(patientTaskAnswersRequest)

            taskResultService.createTaskResult(patient, task, score, patientAnswers)
        }
    }

    private fun checkTasks(game: Game, patientTaskAnswerRequests: List<PatientTaskAnswersRequest<T>>) {
        val tasks = game.tasks
        val taskIds = tasks.map { it.id }
        val patientTaskIds = patientTaskAnswerRequests.map { it.taskId }
        if (tasks.size != patientTaskAnswerRequests.size || !patientTaskIds.containsAll(taskIds)) {
            throw NotAllTasksProvidedException(game.id)
        }
    }

    fun Boolean.toInt() = if (this) 1 else 0
}
