package com.coder_rangers.mobius_api.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "resources")
class Resource(
    @Id
    @field:PositiveOrZero
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    val id: Long,

    @ManyToOne(cascade = [ALL])
    @JoinColumn(name = "game_id", updatable = false, nullable = false)
    @JsonIgnore
    val game: Game,

    @Enumerated(STRING)
    @Column(nullable = false, updatable = false)
    val type: Type,

    @field:NotBlank
    @Column(nullable = false, length = 255)
    val fileName: String
) {
    enum class Type {
        IMAGE,
        TEXT,
        AUDIO;

        @JsonValue
        override fun toString() = name.toLowerCase()
    }
}
