package com.coder_rangers.mobius_api.components.interfaces

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest

interface IGameAnswersResolver<T> {
    fun resolveAnswers(patient: Patient, game: Game, patientTaskAnswersRequestList: List<PatientTaskAnswersRequest<T>>)
    fun getScore(patientTaskAnswersRequest: PatientTaskAnswersRequest<T>, answers: Set<Answer>? = null): Int
    fun transformToPatientAnswers(patientTaskAnswersRequest: PatientTaskAnswersRequest<T>): List<Answer>
}
