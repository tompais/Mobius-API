package com.coder_rangers.mobius_api.models

import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "games")
class Game(
    @Id
    @field:PositiveOrZero
    @Column(unique = true, nullable = false, updatable = false)
    val id: Long,

    @Column(nullable = true, length = 255)
    val description: String,

    @ManyToOne(cascade = [ALL])
    val category: Category,

    @OneToMany(cascade = [ALL])
    val resources: List<Resource>,

    @OneToMany(cascade = [ALL])
    val tasks: List<Task>
)
