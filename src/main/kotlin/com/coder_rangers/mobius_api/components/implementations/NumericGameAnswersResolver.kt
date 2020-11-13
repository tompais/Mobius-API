package com.coder_rangers.mobius_api.components.implementations

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.NumericAnswer
import com.coder_rangers.mobius_api.models.NumericPatientAnswer
import com.coder_rangers.mobius_api.models.PatientAnswer
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class NumericGameAnswersResolver @Autowired constructor(
    taskResultService: ITaskResultService
) : BaseGameAnswersResolver<Int>(taskResultService) {
    override fun getScore(patientTaskAnswersRequest: PatientTaskAnswersRequest<Int>, answers: Set<Answer>?): Int {
        val numericAnswers = answers!!.map { it as NumericAnswer }

        return patientTaskAnswersRequest.patientAnswersRequest.mapIndexed { index, patientAnswer ->
            (numericAnswers[index].number == patientAnswer).toInt()
        }.sum()
    }

    override fun transformToPatientAnswers(patientTaskAnswersRequest: PatientTaskAnswersRequest<Int>): List<PatientAnswer> =
        patientTaskAnswersRequest.patientAnswersRequest.map {
            NumericPatientAnswer(
                number = it
            )
        }
}
