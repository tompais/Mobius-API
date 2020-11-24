package com.coder_rangers.mobius_api.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Basic
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PastOrPresent
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "tasks")
class Task(
    @Id
    @field:PositiveOrZero
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    val id: Long,

    @ManyToOne(cascade = [ALL])
    @JoinColumn(name = "game_id")
    @JsonIgnore
    val game: Game?,

    @field:NotBlank
    @Column(nullable = false, length = 255)
    val description: String,

    @ManyToMany(cascade = [ALL])
    @JoinTable(
        name = "input_task",
        joinColumns = [
            JoinColumn(
                name = "task_id",
                referencedColumnName = "id"
            )
        ],
        inverseJoinColumns = [
            JoinColumn(
                name = "input_id",
                referencedColumnName = "id"
            )
        ]
    )
    val inputs: List<Input>,

    @OneToMany(mappedBy = "task", cascade = [ALL])
    @JsonIgnore
    val answers: Set<Answer>? = null,

    @OneToMany(mappedBy = "task", cascade = [ALL])
    @JsonIgnore
    val results: Set<Result>? = null
) {
    @Entity(name = "Result")
    @Table(name = "task_results")
    class Result(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        @field:PositiveOrZero
        @Column(unique = true, nullable = false, updatable = false)
        val id: Long,

        @ManyToOne
        @JoinColumn(name = "patient_id", updatable = false, nullable = false)
        var patient: Patient?,

        @ManyToOne
        @JoinColumn(name = "task_id", updatable = false, nullable = false)
        val task: Task,

        @OneToMany(mappedBy = "taskResult", cascade = [ALL], orphanRemoval = true)
        val patientAnswers: MutableList<PatientAnswer> = mutableListOf(),

        @field:PositiveOrZero
        @Column(nullable = false, updatable = false)
        val score: Int = 0,

        @Basic
        @CreationTimestamp
        @field:PastOrPresent
        val playedDatetime: LocalDateTime = LocalDateTime.now()
    )
}
