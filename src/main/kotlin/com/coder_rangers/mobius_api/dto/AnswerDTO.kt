package com.coder_rangers.mobius_api.dto

import com.fasterxml.jackson.annotation.JsonValue

class AnswerDTO(
    val type: Type,
    val value: String
) {
    enum class Type {
        TEXT,
        IMAGE;

        @JsonValue
        override fun toString() = name.toLowerCase()
    }
}
