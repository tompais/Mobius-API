package com.coder_rangers.mobius_api.models

import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.ManyToMany
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

    @ManyToMany(mappedBy = "inputs", cascade = [ALL])
    val tasks: Set<Task> = emptySet()
) {
    enum class Type {
        TEXT,
        NUMBER,
        VOICE,
        CALENDAR,
        MAPS;
    }
}
