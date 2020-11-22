package com.coder_rangers.mobius_api.responses

import com.coder_rangers.mobius_api.enums.DementiaLevel

class PatientTestResult(
    val score: Int,

    val dementiaLevel: DementiaLevel
)
