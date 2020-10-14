package com.coder_rangers.mobius_api.models

import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    val id: Long,

    @Enumerated(STRING)
    @Column(nullable = false)
    val type: Type,

    @field:NotBlank
    @Column(nullable = false, length = 255)
    val description: String,

    @OneToMany(mappedBy = "category", cascade = [ALL])
    val games: List<Game>
) {
    enum class Type {
        ORIENTATION,
        FIXATION,
        ATTENTION,
        CALCULATION,
        MEMORY,
        LANGUAGEANDPRAXIA;

        @JsonValue
        override fun toString() = name.toLowerCase()
    }
}
