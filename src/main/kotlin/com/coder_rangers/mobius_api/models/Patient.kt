package com.coder_rangers.mobius_api.models

import com.coder_rangers.mobius_api.enums.Genre
import com.coder_rangers.mobius_api.enums.Genre.OTHER
import com.coder_rangers.mobius_api.models.User.Status.ACTIVE
import org.springframework.data.rest.core.annotation.RestResource
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "patients")
class Patient(
    id: Long,
    firstName: String,
    lastName: String,
    email: String,
    password: String,
    birthday: LocalDate,

    @ManyToMany(mappedBy = "patients", cascade = [ALL])
    @field:NotEmpty
    val guardians: Set<Guardian>,

    genre: Genre = OTHER,
    status: Status = ACTIVE,
    lastUpdate: LocalDateTime = LocalDateTime.now(),

    @OneToOne(mappedBy = "patient", cascade = [ALL])
    @RestResource(path = "test-progress", rel = "test-progress")
    val testProgress: TestProgress? = null,

    @OneToMany(mappedBy = "patient", cascade = [ALL])
    val taskResults: Set<Task.Result>? = null
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
