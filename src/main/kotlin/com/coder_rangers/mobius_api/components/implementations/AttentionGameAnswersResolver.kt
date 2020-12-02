package com.coder_rangers.mobius_api.components.implementations

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.Answer.Type.PATIENT
import com.coder_rangers.mobius_api.models.CharAnswer
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AttentionGameAnswersResolver @Autowired constructor(
    taskResultService: ITaskResultService
) : BaseGameAnswersResolver<Char>(taskResultService) {
    override fun getScore(patientTaskAnswersRequest: PatientTaskAnswersRequest<Char>, answers: Set<Answer>?): Int {
        val charAnswers = answers!!.map { it as CharAnswer }

        return patientTaskAnswersRequest.patientAnswersRequest.mapIndexed { index, patientAnswer ->
            (charAnswers[index].letter.equals(patientAnswer, ignoreCase = true)).toInt()
        }.sum()
    }

    override fun transformToPatientAnswers(patientTaskAnswersRequest: PatientTaskAnswersRequest<Char>): List<Answer> =
        patientTaskAnswersRequest.patientAnswersRequest.map {
            CharAnswer(
                letter = it,
                type = PATIENT
            )
        }
}
