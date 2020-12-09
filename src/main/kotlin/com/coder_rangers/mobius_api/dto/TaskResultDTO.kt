package com.coder_rangers.mobius_api.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.PositiveOrZero

class TaskResultDTO(
    @field:NotBlank
    val description: String,

    @field:NotEmpty
    val patientAnswers: List<AnswerDTO>,

    @field:NotEmpty
    val expectedAnswers: List<AnswerDTO>,

    @field:PositiveOrZero
    val score: Int
)
