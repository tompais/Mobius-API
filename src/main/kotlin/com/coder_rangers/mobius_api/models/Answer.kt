package com.coder_rangers.mobius_api.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
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
    open val id: Long = 0,

    @JsonIgnore
    @Enumerated(STRING)
    open val type: Type,

    @JsonIgnore
    @ManyToOne(cascade = [ALL])
    @JoinColumn(name = "task_id")
    open val task: Task? = null,

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "task_result_id")
    open var taskResult: Task.Result? = null,

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "input_id")
    open val input: Input? = null
) {
    override fun hashCode(): Int {
        return id.toInt()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Answer) return false

        if (id != other.id) return false
        if (type != other.type) return false
        if (task != other.task) return false
        if (taskResult != other.taskResult) return false
        if (input != other.input) return false

        return true
    }

    enum class Type {
        EXPECTED,
        PATIENT,
        POSSIBLE;

        @JsonValue
        override fun toString() = name.toLowerCase()
    }
}
