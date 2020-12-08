package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.ITaskResultDAO
import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.Task
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import com.coder_rangers.mobius_api.utils.mapToCategoriesWithPercentages
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
        val categoriesWithAverage = taskResultDAO.getPatientResults(patientId, gameCategories)
            .mapToCategoriesWithPercentages()

        return categoriesWithAverage.minByOrNull { it.second }!!.first
    }
}
