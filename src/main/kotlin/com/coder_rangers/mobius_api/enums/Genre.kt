package com.coder_rangers.mobius_api.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class Genre {
    MALE,
    FEMALE,
    OTHER;

    @JsonValue
    override fun toString() = name.toLowerCase()
}
