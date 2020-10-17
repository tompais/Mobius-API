package com.coder_rangers.mobius_api.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "numeric_answers")
class NumericAnswer(
    id: Long,
    task: Task,

    @Column(nullable = false, updatable = false)
    val number: Double
) : Answer(id, task)
