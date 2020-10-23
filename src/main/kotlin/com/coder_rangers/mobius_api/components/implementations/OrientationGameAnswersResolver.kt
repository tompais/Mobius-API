package com.coder_rangers.mobius_api.components.implementations

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.requests.PatientTaskAnswers
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrientationGameAnswersResolver @Autowired constructor(
    taskResultService: ITaskResultService
) : BaseGameAnswersResolver<Boolean>(taskResultService) {
    override fun getScore(patientTaskAnswers: PatientTaskAnswers<Boolean>, answers: Set<Answer>?): Int {
        return patientTaskAnswers.patientAnswers.map {
            it.toInt()
        }.sum()
    }
}
