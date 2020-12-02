package com.coder_rangers.mobius_api.components.implementations

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.Answer.Type.PATIENT
import com.coder_rangers.mobius_api.models.AnswerWithResult
import com.coder_rangers.mobius_api.models.TextAnswer
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TextGameAnswersWithResultsResolver @Autowired constructor(
    taskResultService: ITaskResultService
) : BaseGameAnswersResolver<AnswerWithResult<String>>(taskResultService) {
    override fun getScore(
        patientTaskAnswersRequest: PatientTaskAnswersRequest<AnswerWithResult<String>>,
        answers: Set<Answer>?
    ): Int = patientTaskAnswersRequest.patientAnswersRequest.sumBy {
        it.isCorrect.toInt()
    }

    override fun transformToPatientAnswers(patientTaskAnswersRequest: PatientTaskAnswersRequest<AnswerWithResult<String>>): List<Answer> =
        patientTaskAnswersRequest.patientAnswersRequest.map {
            TextAnswer(
                text = it.answer,
                type = PATIENT
            )
        }
}
