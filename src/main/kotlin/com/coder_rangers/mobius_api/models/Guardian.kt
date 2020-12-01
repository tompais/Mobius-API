package com.coder_rangers.mobius_api.models

import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "guardians")
class Guardian(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @field:PositiveOrZero
    @Column(unique = true, nullable = false, updatable = false)
    val id: Long,

    @field:Email
    @Column(nullable = false, updatable = false, unique = true)
    val email: String,

    @ManyToMany(cascade = [ALL])
    @JoinTable(
        name = "patient_guardian",
        joinColumns = [
            JoinColumn(
                name = "guardian_id",
                referencedColumnName = "id",
                nullable = false
            ),
        ],
        inverseJoinColumns = [
            JoinColumn(
                name = "patient_id",
                referencedColumnName = "id"
            )
        ]
    )
    val patients: Set<Patient>? = null
)
