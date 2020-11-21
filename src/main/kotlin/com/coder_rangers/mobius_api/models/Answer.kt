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
@Table(name = "answers")
@Inheritance(strategy = JOINED)
open class Answer(
    @Id
    @field:PositiveOrZero
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    open val id: Long,

    @ManyToOne(cascade = [ALL])
    @JoinColumn(name = "task_id", nullable = false)
    open val task: Task,
) {
    override fun hashCode(): Int {
        return id.toInt()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Answer) return false

        if (id != other.id) return false
        if (task != other.task) return false

        return true
    }
}
