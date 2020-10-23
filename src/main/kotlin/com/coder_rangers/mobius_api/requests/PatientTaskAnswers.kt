package com.coder_rangers.mobius_api.requests

class PatientTaskAnswers<T>(
    val taskId: Long,

    val patientAnswers: List<T>
)
