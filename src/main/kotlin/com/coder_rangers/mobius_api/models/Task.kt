package com.coder_rangers.mobius_api.models

import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "tasks")
class Task(
    @Id
    @field:PositiveOrZero
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    val id: Long,

    @ManyToOne(cascade = [ALL])
    @JoinColumn(name = "game_id")
    val game: Game,

    @field:NotBlank
    @Column(nullable = false, length = 255)
    val description: String,

    @OneToMany(mappedBy = "task", cascade = [ALL])
    val answers: List<Answer>? = null
)
