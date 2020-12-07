package com.coder_rangers.mobius_api.models

import com.coder_rangers.mobius_api.enums.Genre
import com.coder_rangers.mobius_api.enums.Genre.OTHER
import com.coder_rangers.mobius_api.enums.TestStatus
import com.coder_rangers.mobius_api.enums.TestStatus.IN_PROGRESS
import com.coder_rangers.mobius_api.models.Patient.Status.ACTIVE
import com.coder_rangers.mobius_api.validators.annotations.OverEighteen
import com.fasterxml.jackson.annotation.JsonValue
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Basic
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Past
import javax.validation.constraints.PastOrPresent
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "patients")
class Patient(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @field:PositiveOrZero
    @Column(unique = true, nullable = false, updatable = false)
    val id: Long = 0,

    @field:NotBlank
    @Column(nullable = false, length = 20)
    val firstName: String,

    @field:NotBlank
    @Column(nullable = false, length = 20)
    val lastName: String,

    @field:NotBlank
    @field:Email
    @Column(unique = true, nullable = false, length = 30)
    val email: String,

    @field:NotBlank
    @Column(nullable = false, updatable = true, length = 200)
    val password: String,

    @Basic
    @field:Past
    @field:OverEighteen
    @Column(nullable = false)
    val birthday: LocalDate,

    @ManyToMany(mappedBy = "patients", cascade = [ALL])
    val guardians: Set<Guardian>,

    @Enumerated(STRING)
    @Column(nullable = false, updatable = false)
    val genre: Genre = OTHER,

    @Enumerated(STRING)
    @Column(nullable = false)
    val status: Status = ACTIVE,

    @Enumerated(STRING)
    var testStatus: TestStatus = IN_PROGRESS,

    @Basic
    @UpdateTimestamp
    @field:PastOrPresent
    private val lastUpdate: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "patient", cascade = [ALL], orphanRemoval = true)
    val taskResults: MutableSet<Task.Result>? = null
) {
    enum class Status {
        ACTIVE,
        INACTIVE;

        @JsonValue
        override fun toString() = name.toLowerCase()
    }

    fun getTestTaskResults(): List<Task.Result> = taskResults!!.filter { taskResult ->
        taskResult.task.game!!.isTestGame
    }
}
