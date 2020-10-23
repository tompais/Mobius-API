package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.TextAnswer
import com.coder_rangers.mobius_api.requests.PatientTaskAnswers
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FixationGameAnswersResolverService @Autowired constructor(
    taskResultService: ITaskResultService
) : BaseGameAnswersResolverService<String>(taskResultService) {
    override fun getScore(patientTaskAnswers: PatientTaskAnswers<String>, answers: Set<Answer>?): Int {
        val textAnswers = answers!!.map { it as TextAnswer }

        return patientTaskAnswers.patientAnswers.map { patientAnswer ->
            textAnswers.any { textAnswer ->
                textAnswer.text.toLowerCase() == patientAnswer.toLowerCase()
            }.toInt()
        }.sum()
    }
}

private fun Boolean.toInt() = if (this) 1 else 0
