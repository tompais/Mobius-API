package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.PatientTaskAnswers

interface IGameAnswersResolverService<T> {
    fun resolveAnswers(patient: Patient, game: Game, patientTaskAnswersList: List<PatientTaskAnswers<T>>, isTest: Boolean = true)
    fun getScore(patientTaskAnswers: PatientTaskAnswers<T>, answers: Set<Answer>? = null): Int
}
