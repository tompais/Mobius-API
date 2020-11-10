package com.coder_rangers.mobius_api.requests

class PatientTaskAnswersRequest<T>(
    val taskId: Long,

    val patientAnswersRequest: List<T>
)
