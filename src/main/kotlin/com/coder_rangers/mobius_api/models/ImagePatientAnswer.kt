package com.coder_rangers.mobius_api.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "image_patient_answers")
class ImagePatientAnswer(
    @Column(nullable = false, updatable = false)
    val imageName: String,

    id: Long = 0,
    taskResult: Task.Result? = null
) : PatientAnswer(
    id,
    taskResult
)
