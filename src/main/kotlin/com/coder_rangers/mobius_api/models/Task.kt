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
@Table(name = "tasks")
class Task(
    @Id
    @field:PositiveOrZero
    @Column(unique = true, nullable = false, updatable = false)
    val id: Long,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val game: Game,

    @field:NotBlank
    @Column(nullable = false, length = 255)
    val description: String
)
