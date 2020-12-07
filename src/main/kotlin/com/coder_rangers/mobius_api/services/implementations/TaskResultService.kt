package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.ITaskResultDAO
import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.Answer.Type.EXPECTED
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Patient
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
    override fun createTaskResult(
        patient: Patient,
        task: Task,
        score: Int,
        patientAnswers: List<Answer>
    ): Task.Result =
        taskResultDAO.createTaskResult(patient, task, score, patientAnswers)

    override fun getRecommendedCategory(patientId: Long, gameCategories: List<Category>): Category {
        val patientResultsGroupedByCategory = taskResultDAO.getPatientResults(patientId, gameCategories)
            .groupBy { it.task.game!!.category }

        val categoriesWithAverage =
            patientResultsGroupedByCategory.map { patientResult ->
                patientResult.key to patientResult.value.sumBy { result -> result.score } * 100 / patientResult.value.map { result -> result.task.answers!!.count { answer -> answer.type == EXPECTED } }
                    .sum()
            }

        return categoriesWithAverage.minByOrNull { it.second }!!.first
    }
}
