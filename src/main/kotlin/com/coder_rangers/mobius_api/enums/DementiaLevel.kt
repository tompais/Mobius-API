package com.coder_rangers.mobius_api.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class DementiaLevel(
    val displayName: String
) {
    NO_DEMENTIA("Sin demencia"),
    POSSIBLE_DEMENTIA("Posible"),
    MILD_TO_MODERATE_DEMENTIA("Media a moderada"),
    MODERATE_TO_SEVERE_DEMENTIA("Moderada a severa"),
    SEVERE_DEMENTIA("Severa");

    @JsonValue
    override fun toString() = name.toLowerCase()
}
