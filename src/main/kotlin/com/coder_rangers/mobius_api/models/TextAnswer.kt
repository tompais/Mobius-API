package com.coder_rangers.mobius_api.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "text_answers")
class TextAnswer(
    id: Long,
    task: Task,

    @Column(nullable = false, updatable = false)
    @field:NotBlank
    val text: String
) : Answer(id, task)
