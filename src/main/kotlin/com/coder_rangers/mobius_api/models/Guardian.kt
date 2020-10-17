package com.coder_rangers.mobius_api.models

import com.coder_rangers.mobius_api.enums.Genre
import com.coder_rangers.mobius_api.enums.Genre.OTHER
import com.coder_rangers.mobius_api.models.User.Status.ACTIVE
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "guardians")
class Guardian(
    id: Long,
    firstName: String,
    lastName: String,
    email: String,
    password: String,
    birthday: LocalDate,
    genre: Genre = OTHER,
    status: Status = ACTIVE,
    lastUpdate: LocalDateTime = LocalDateTime.now(),

    @ManyToMany(cascade = [ALL])
    @JoinTable(
        name = "patient_guardian",
        joinColumns = [
            JoinColumn(
                name = "patient_id",
                referencedColumnName = "id"
            ),
        ],
        inverseJoinColumns = [
            JoinColumn(
                name = "guardian_id",
                referencedColumnName = "id",
                nullable = false
            )
        ]
    )
    val patients: Set<Patient>? = null
) : User(
    id,
    firstName,
    lastName,
    email,
    password,
    birthday,
    genre,
    status,
    lastUpdate
)
