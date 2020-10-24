package com.coder_rangers.mobius_api.components.implementations

import com.coder_rangers.mobius_api.components.interfaces.IGameAnswersResolver
import com.coder_rangers.mobius_api.error.exceptions.NotAllTasksProvidedException
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.PatientTaskAnswers
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService

abstract class BaseGameAnswersResolver<T>(
    private val taskResultService: ITaskResultService
) : IGameAnswersResolver<T> {
    override fun resolveAnswers(
        patient: Patient,
        game: Game,
        patientTaskAnswersList: List<PatientTaskAnswers<T>>,
        isTest: Boolean
    ) {
        checkTasks(game, patientTaskAnswersList)
        game.tasks.forEach { task ->
            val score = getScore(patientTaskAnswersList.first { it.taskId == task.id }, task.answers)

            taskResultService.createTaskResult(patient, task, score, isTest)
        }
    }

    private fun checkTasks(game: Game, patientTaskAnswers: List<PatientTaskAnswers<T>>) {
        val tasks = game.tasks
        val taskIds = tasks.map { it.id }
        val patientTaskIds = patientTaskAnswers.map { it.taskId }
        if (tasks.size != patientTaskAnswers.size || !patientTaskIds.containsAll(taskIds)) {
            throw NotAllTasksProvidedException(game.id)
        }
    }

    fun Boolean.toInt() = if (this) 1 else 0
}