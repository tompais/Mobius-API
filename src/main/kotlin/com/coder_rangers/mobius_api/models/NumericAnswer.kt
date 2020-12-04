package com.coder_rangers.mobius_api.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "numeric_answers")
class NumericAnswer(
    id: Long = 0,
    type: Type,
    task: Task? = null,
    taskResult: Task.Result? = null,

    @Column(nullable = false, updatable = false)
    val number: Int
) : Answer(
    id,
    type,
    task,
    taskResult
)
