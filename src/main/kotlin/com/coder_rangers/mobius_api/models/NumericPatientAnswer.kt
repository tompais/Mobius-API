package com.coder_rangers.mobius_api.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "numeric_patient_answers")
class NumericPatientAnswer(
    @Column(nullable = false, updatable = false)
    val number: Int,

    id: Long = 0,
    taskResult: Task.Result? = null
) : PatientAnswer(
    id,
    taskResult
)
