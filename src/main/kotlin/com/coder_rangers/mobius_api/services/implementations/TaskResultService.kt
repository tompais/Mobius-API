package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.ITaskResultDAO
import com.coder_rangers.mobius_api.enums.DementiaLevel
import com.coder_rangers.mobius_api.enums.DementiaLevel.MILD_TO_MODERATE_DEMENTIA
import com.coder_rangers.mobius_api.enums.DementiaLevel.MODERATE_TO_SEVERE_DEMENTIA
import com.coder_rangers.mobius_api.enums.DementiaLevel.NO_DEMENTIA
import com.coder_rangers.mobius_api.enums.DementiaLevel.POSSIBLE_DEMENTIA
import com.coder_rangers.mobius_api.enums.DementiaLevel.SEVERE_DEMENTIA
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.Task
import com.coder_rangers.mobius_api.responses.PatientTestResult
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

    override fun getPatientTestResult(patientId: Long): PatientTestResult {
        val testTotalScore = taskResultDAO.getTestTotalScore(patientId)

        val dementiaLevel: DementiaLevel = when (testTotalScore) {
            in 30..35 -> NO_DEMENTIA
            in 25..29 -> POSSIBLE_DEMENTIA
            in 20..24 -> MILD_TO_MODERATE_DEMENTIA
            in 15..19 -> MODERATE_TO_SEVERE_DEMENTIA
            else -> SEVERE_DEMENTIA
        }

        return PatientTestResult(testTotalScore, dementiaLevel)
    }

    override fun getRecommendedCategory(patientId: Long, gameCategories: List<Category>): Category {
        val patientResultsGroupedByCategory = taskResultDAO.getPatientResults(patientId, gameCategories)
            .groupBy { it.task.game!!.category }

        val categoriesWithAverage =
            patientResultsGroupedByCategory.map { patientResult ->
                patientResult.key to patientResult.value.sumBy { result -> result.score } * 100 / patientResult.value.map { result -> result.task.inputs.count() }
                    .sum()
            }

        return categoriesWithAverage.minByOrNull { it.second }!!.first
    }
}
