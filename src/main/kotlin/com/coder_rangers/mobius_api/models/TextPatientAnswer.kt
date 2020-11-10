package com.coder_rangers.mobius_api.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "text_patient_answers")
class TextPatientAnswer(
    @Column(nullable = false, updatable = false)
    @field:NotBlank
    val text: String,

    id: Long = 0,
    taskResult: Task.Result? = null
) : PatientAnswer(
    id,
    taskResult
)
