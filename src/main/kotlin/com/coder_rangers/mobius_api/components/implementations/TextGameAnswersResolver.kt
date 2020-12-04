package com.coder_rangers.mobius_api.components.implementations

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.Answer.Type.PATIENT
import com.coder_rangers.mobius_api.models.TextAnswer
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TextGameAnswersResolver @Autowired constructor(
    taskResultService: ITaskResultService
) : BaseGameAnswersResolver<String>(taskResultService) {
    override fun getScore(patientTaskAnswersRequest: PatientTaskAnswersRequest<String>, answers: Set<Answer>?): Int {
        val textAnswers = answers!!.map { it as TextAnswer }

        return patientTaskAnswersRequest.patientAnswersRequest.map {
            it.toLowerCase()
        }.toSet().sumBy { patientAnswer ->
            textAnswers.any { textAnswer ->
                textAnswer.text.equals(patientAnswer, ignoreCase = true)
            }.toInt()
        }
    }

    override fun transformToPatientAnswers(patientTaskAnswersRequest: PatientTaskAnswersRequest<String>): List<Answer> =
        patientTaskAnswersRequest.patientAnswersRequest.map {
            TextAnswer(
                text = it,
                type = PATIENT
            )
        }
}
