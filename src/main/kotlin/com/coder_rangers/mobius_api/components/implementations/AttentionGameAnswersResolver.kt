package com.coder_rangers.mobius_api.components.implementations

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.TextAnswer
import com.coder_rangers.mobius_api.requests.PatientTaskAnswers
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AttentionGameAnswersResolver @Autowired constructor(
    taskResultService: ITaskResultService
) : BaseGameAnswersResolver<String>(taskResultService) {
    override fun getScore(patientTaskAnswers: PatientTaskAnswers<String>, answers: Set<Answer>?): Int {
        val textAnswers = answers!!.map { it as TextAnswer }

        return patientTaskAnswers.patientAnswers.map {
            it.toLowerCase()
        }.toSet().map { patientAnswer ->
            textAnswers.any { textAnswer ->
                textAnswer.text.toLowerCase() == patientAnswer
            }.toInt()
        }.sum()
    }
}
