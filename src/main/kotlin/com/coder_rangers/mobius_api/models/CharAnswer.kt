package com.coder_rangers.mobius_api.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "char_answers")
class CharAnswer(
    id: Long,
    task: Task,

    @Column(nullable = false, updatable = false)
    val letter: Char,
) : Answer(id, task)
