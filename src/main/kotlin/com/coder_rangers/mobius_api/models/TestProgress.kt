package com.coder_rangers.mobius_api.models

import com.coder_rangers.mobius_api.models.TestProgress.Status.IN_PROGRESS
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
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "test_progresses")
class TestProgress(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @field:PositiveOrZero
    @Column(nullable = false, unique = true, updatable = false)
    val id: Long,

    @OneToOne(cascade = [ALL])
    @JoinColumn(name = "patient_id", unique = true, updatable = false, nullable = false)
    val patient: Patient,

    @Enumerated(STRING)
    var lastCategoryPlayed: Game.Category,

    @Enumerated(STRING)
    @Column(nullable = false)
    var status: Status = IN_PROGRESS
) {
    enum class Status {
        IN_PROGRESS,
        FINISHED;

        @JsonValue
        override fun toString() = name.toLowerCase()
    }
}
