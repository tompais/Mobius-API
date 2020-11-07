package com.coder_rangers.mobius_api.components.implementations

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.NumericAnswer
import com.coder_rangers.mobius_api.requests.PatientTaskAnswers
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CalculationGameAnswersResolver @Autowired constructor(
    taskResultService: ITaskResultService
) : BaseGameAnswersResolver<Int>(taskResultService) {
    override fun getScore(patientTaskAnswers: PatientTaskAnswers<Int>, answers: Set<Answer>?): Int {
        val numericAnswers = answers!!.map { it as NumericAnswer }

        return patientTaskAnswers.patientAnswers.mapIndexed { index, patientAnswer ->
            (numericAnswers[index].number == patientAnswer).toInt()
        }.sum()
    }
}
