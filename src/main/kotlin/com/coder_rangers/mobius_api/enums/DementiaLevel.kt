package com.coder_rangers.mobius_api.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class DementiaLevel {
    NO_DEMENTIA,
    POSSIBLE_DEMENTIA,
    MILD_TO_MODERATE_DEMENTIA,
    MODERATE_TO_SEVERE_DEMENTIA,
    SEVERE_DEMENTIA;

    @JsonValue
    override fun toString() = name.toLowerCase()
}
