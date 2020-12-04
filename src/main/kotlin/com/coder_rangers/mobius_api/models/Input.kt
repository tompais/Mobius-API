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
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "inputs")
class Input(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @field:PositiveOrZero
    @Column(nullable = false, updatable = false, unique = true)
    val id: Long,

    @Enumerated(STRING)
    @Column(nullable = false, updatable = false)
    val type: Type,

    @JsonIgnore
    @ManyToOne(cascade = [ALL])
    @JoinColumn(name = "task_id")
    val task: Task,

    @OneToMany(mappedBy = "input")
    val possibleAnswers: Set<Answer> = emptySet()
) {
    enum class Type {
        TEXT,
        NUMBER,
        VOICE,
        CALENDAR,
        COUNTRY,
        STATE,
        CITY,
        DRAWING_PAD,
        SELECT;

        @JsonValue
        override fun toString() = name.toLowerCase()
    }
}
