package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.error.exceptions.NotAllTasksProvidedException
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.PatientTaskAnswers
import com.coder_rangers.mobius_api.services.interfaces.IGameAnswersResolverService
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService

abstract class BaseGameAnswersResolverService<T>(
    private val taskResultService: ITaskResultService
) : IGameAnswersResolverService<T> {
    override fun resolveAnswers(patient: Patient, game: Game, patientTaskAnswersList: List<PatientTaskAnswers<T>>, isTest: Boolean) {
        checkTasks(game, patientTaskAnswersList)
        game.tasks.forEach { task ->
            val score = getScore(patientTaskAnswersList.first { it.taskId == task.id }, task.answers)

            taskResultService.createTaskResult(patient, task, score, isTest)
        }
    }

    private fun checkTasks(game: Game, patientTaskAnswers: List<PatientTaskAnswers<T>>) {
        val tasks = game.tasks

        if (tasks.size != patientTaskAnswers.size || !patientTaskAnswers.map { it.taskId }.containsAll(tasks.map { it.id })) {
            throw NotAllTasksProvidedException(game.id)
        }
    }
}
