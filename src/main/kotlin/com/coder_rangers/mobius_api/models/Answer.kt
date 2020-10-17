package com.coder_rangers.mobius_api.models

import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType.JOINED
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.validation.constraints.PositiveOrZero

@Entity
@Inheritance(strategy = JOINED)
open class Answer(
    @Id
    @field:PositiveOrZero
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    val id: Long,

    @ManyToOne(cascade = [ALL])
    @JoinColumn(name = "task_id", nullable = false)
    val task: Task,
)
