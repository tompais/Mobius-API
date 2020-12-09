package com.coder_rangers.mobius_api.responses

import com.coder_rangers.mobius_api.dto.TaskResultDTO
import com.coder_rangers.mobius_api.enums.DementiaLevel
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.responses.TestResult.Type.COMPLETE
import com.coder_rangers.mobius_api.responses.TestResult.Type.GENERAL
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonValue
import java.time.LocalDate
import javax.validation.constraints.PositiveOrZero

abstract class TestResult(
    @field:PositiveOrZero
    val score: Int,

    val dementiaLevel: DementiaLevel,

    @JsonIgnore
    val type: Type
) {
    enum class Type {
        GENERAL,
        COMPLETE;

        @JsonValue
        override fun toString() = name.toLowerCase()
    }
}

class GeneralTestResult(
    score: Int,
    dementiaLevel: DementiaLevel
) : TestResult(
    score,
    dementiaLevel,
    GENERAL
)

class CompleteTestResult(
    score: Int,
    dementiaLevel: DementiaLevel,
    val patientFirstName: String,
    val patientLastName: String,
    val testPlayedDate: LocalDate,
    val gameResults: List<GameResult>
) : TestResult(
    score,
    dementiaLevel,
    COMPLETE
)

class GameResult(
    val category: Game.Category,
    val taskResults: List<TaskResultDTO>
)
