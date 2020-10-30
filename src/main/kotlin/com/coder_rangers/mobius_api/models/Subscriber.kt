package com.coder_rangers.mobius_api.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "subscribers")
class Subscriber(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    @field:PositiveOrZero
    val id: Long,

    @field:NotBlank
    @Column(nullable = false, updatable = false)
    val firstName: String,

    @field:NotBlank
    @Column(nullable = false, updatable = false)
    val lastName: String,

    @field:NotBlank
    @field:Email
    @Column(nullable = false, updatable = false, unique = true)
    val email: String
)
