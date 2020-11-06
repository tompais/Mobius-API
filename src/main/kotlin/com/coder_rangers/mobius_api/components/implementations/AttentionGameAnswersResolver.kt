package com.coder_rangers.mobius_api.components.implementations

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.CharAnswer
import com.coder_rangers.mobius_api.requests.PatientTaskAnswers
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AttentionGameAnswersResolver @Autowired constructor(
    taskResultService: ITaskResultService
) : BaseGameAnswersResolver<Char>(taskResultService) {
    override fun getScore(patientTaskAnswers: PatientTaskAnswers<Char>, answers: Set<Answer>?): Int {
        val charAnswers = answers!!.map { it as CharAnswer }

        return patientTaskAnswers.patientAnswers.map {
            it.toLowerCase()
        }.toList().map { patientAnswer ->
            charAnswers.any { charAnswer ->
                charAnswer.letter.toLowerCase() == patientAnswer
            }.toInt()
        }.sum()
    }
}
