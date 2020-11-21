package com.coder_rangers.mobius_api.responses

import com.coder_rangers.mobius_api.responses.PatientTestResult.DementiaLevel.MILD_TO_MODERATE_DEMENTIA
import com.coder_rangers.mobius_api.responses.PatientTestResult.DementiaLevel.MODERATE_TO_SEVERE_DEMENTIA
import com.coder_rangers.mobius_api.responses.PatientTestResult.DementiaLevel.NO_DEMENTIA
import com.coder_rangers.mobius_api.responses.PatientTestResult.DementiaLevel.POSSIBLE_DEMENTIA
import com.coder_rangers.mobius_api.responses.PatientTestResult.DementiaLevel.SEVERE_DEMENTIA
import com.fasterxml.jackson.annotation.JsonValue

class PatientTestResult(
    val score: Int
) {
    val result: DementiaLevel = when (score) {
        in 27..30 -> NO_DEMENTIA
        25, 26 -> POSSIBLE_DEMENTIA
        in 10..24 -> MILD_TO_MODERATE_DEMENTIA
        in 6..9 -> MODERATE_TO_SEVERE_DEMENTIA
        else -> SEVERE_DEMENTIA
    }

    enum class DementiaLevel {
        NO_DEMENTIA,
        POSSIBLE_DEMENTIA,
        MILD_TO_MODERATE_DEMENTIA,
        MODERATE_TO_SEVERE_DEMENTIA,
        SEVERE_DEMENTIA;

        @JsonValue
        override fun toString() = name.toLowerCase()
    }
}
