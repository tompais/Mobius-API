package com.coder_rangers.mobius_api.models

import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "resources")
class Resource(
    @Id
    @field:PositiveOrZero
    @Column(unique = true, nullable = false, updatable = false)
    val id: Long,

    @ManyToOne(cascade = [ALL])
    val game: Game,

    @field:NotBlank
    @Column(nullable = false, length = 10)
    val type: String,

    @field:NotBlank
    @Column(nullable = false, length = 255)
    val file_name: String
)
