package com.coder_rangers.mobius_api.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "char_answers")
class CharAnswer(
    id: Long = 0,
    type: Type,
    task: Task? = null,
    taskResult: Task.Result? = null,

    @Column(nullable = false, updatable = false)
    val letter: Char,
) : Answer(
    id,
    type,
    task,
    taskResult
)
