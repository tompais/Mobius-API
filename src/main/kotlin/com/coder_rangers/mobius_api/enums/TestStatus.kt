package com.coder_rangers.mobius_api.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class TestStatus {
    IN_PROGRESS,
    FINISHED;

    @JsonValue
    override fun toString() = name.toLowerCase()
}
