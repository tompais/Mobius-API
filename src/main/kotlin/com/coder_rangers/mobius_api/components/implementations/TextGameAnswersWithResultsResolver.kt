package com.coder_rangers.mobius_api.components.implementations

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.AnswerWithResult
import com.coder_rangers.mobius_api.models.PatientAnswer
import com.coder_rangers.mobius_api.models.TextPatientAnswer
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
    ): Int = patientTaskAnswersRequest.patientAnswersRequest.map {
        it.isCorrect.toInt()
    }.sum()

    override fun transformToPatientAnswers(patientTaskAnswersRequest: PatientTaskAnswersRequest<AnswerWithResult<String>>): List<PatientAnswer> =
        patientTaskAnswersRequest.patientAnswersRequest.map {
            TextPatientAnswer(
                text = it.answer
            )
        }
}
