package com.coder_rangers.mobius_api.models

import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType.JOINED
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "patient_answers")
@Inheritance(strategy = JOINED)
open class PatientAnswer(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    @field:PositiveOrZero
    open val id: Long = 0,

    @ManyToOne(cascade = [ALL])
    @JoinColumn(name = "task_result_id")
    open var taskResult: Task.Result? = null
)
