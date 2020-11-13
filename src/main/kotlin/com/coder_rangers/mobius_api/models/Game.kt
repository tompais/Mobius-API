package com.coder_rangers.mobius_api.models

import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "games")
class Game(
    @Id
    @field:PositiveOrZero
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    val id: Long,

    @field:NotBlank
    @Column(nullable = false, unique = true, length = 50)
    val name: String,

    @Column(length = 255)
    val description: String,

    @field:NotBlank
    @Enumerated(STRING)
    val category: Category,

    @OneToMany(mappedBy = "game", cascade = [ALL])
    @field:NotEmpty
    val tasks: List<Task>,

    val isTest: Boolean = false,

    @OneToMany(mappedBy = "game", cascade = [ALL])
    val resources: List<Resource>? = null
) {
    enum class Category {
        ORIENTATION,
        FIXATION,
        CALCULATION,
        ATTENTION,
        MEMORY,
        VISUALIZATION,
        REPETITION,
        COMPREHENSION;

        @JsonValue
        override fun toString() = name.toLowerCase()
    }
}
