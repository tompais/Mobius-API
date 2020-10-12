package com.coder_rangers.mobius_api.models

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "answers")
class Answer(
    @Id
    @field:PositiveOrZero
    @Column(unique = true, nullable = false, updatable = false)
    val id: Long,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val task: Task,

    @field:NotBlank
    @Column(nullable = false, length = 10)
    val answer_type: String,

    @field:NotBlank
    @Column(nullable = false, length = 255)
    val text: String
)
