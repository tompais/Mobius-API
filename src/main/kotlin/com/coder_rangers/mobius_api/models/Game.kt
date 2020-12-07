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
import javax.validation.constraints.Positive
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
    @Column(nullable = false, length = 50)
    val name: String,

    @Column(length = 255)
    val description: String,

    @field:NotBlank
    @Enumerated(STRING)
    val category: Category,

    @OneToMany(mappedBy = "game", cascade = [ALL])
    @field:NotEmpty
    val tasks: List<Task>,

    val isTestGame: Boolean = false,

    @OneToMany(mappedBy = "game", cascade = [ALL])
    val resources: List<Resource>? = null
) {
    enum class Category(
        @field:NotBlank
        val displayName: String,

        @field:Positive
        val maxTestScore: Int
    ) {
        ORIENTATION("Orientación", 8),
        FIXATION("Fijación", 3),
        CALCULATION("Cálculo", 5),
        ATTENTION("Atención", 5),
        MEMORY("Memoria", 3),
        VISUALIZATION("Visualización", 1),
        REPETITION("Repetición", 1),
        COMPREHENSION("Comprensión", 3),
        READING("Lectura", 1),
        WRITING("Escritura", 1),
        DRAWING("Dibujo", 1);

        @JsonValue
        override fun toString() = name.toLowerCase()
    }
}
