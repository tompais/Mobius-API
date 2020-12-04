package com.coder_rangers.mobius_api.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "image_answers")
class ImageAnswer(
    id: Long = 0,
    type: Type,
    task: Task? = null,
    taskResult: Task.Result? = null,

    @field:NotBlank
    @Column(nullable = false, updatable = false, unique = true)
    val imageName: String,
) : Answer(
    id,
    type,
    task,
    taskResult
)
