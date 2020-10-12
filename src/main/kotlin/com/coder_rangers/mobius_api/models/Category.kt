package com.coder_rangers.mobius_api.models

import com.coder_rangers.mobius_api.enums.CategoryType
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "categories")
class Category(
    @Id
    @field:PositiveOrZero
    @Column(unique = true, nullable = false, updatable = false)
    val id: Long,

    @Enumerated(STRING)
    @field:NotBlank
    @Column(nullable = false)
    val type: CategoryType,

    @field:NotBlank
    @Column(nullable = false, length = 255)
    val description: String,

    @OneToMany(cascade = [ALL])
    val games: List<Game>
)
