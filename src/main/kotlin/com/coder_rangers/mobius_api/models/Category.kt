package com.coder_rangers.mobius_api.models

import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "categories")
class Category(
    @Id
    @field:PositiveOrZero
    @Column(unique = true, nullable = false, updatable = false)
    val id: Long,

    @field:NotBlank
    @Column(nullable = false, length = 15)
    val type: String,

    @field:NotBlank
    @Column(nullable = false, length = 255)
    val description: String,

    @OneToMany(cascade = [ALL])
    val games: List<Game>
)
