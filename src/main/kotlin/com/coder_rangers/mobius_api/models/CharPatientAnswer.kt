package com.coder_rangers.mobius_api.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "char_patient_answers")
class CharPatientAnswer(
    @Column(nullable = false, updatable = false)
    val letter: Char,

    id: Long = 0,
    taskResult: Task.Result? = null
) : PatientAnswer(
    id,
    taskResult
)
