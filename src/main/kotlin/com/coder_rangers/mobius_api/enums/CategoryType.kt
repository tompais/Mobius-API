package com.coder_rangers.mobius_api.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class CategoryType {
    ORIENTATION,
    FIXING,
    ATTENTION,
    CALCULATION,
    MEMORY,
    LANGUAGEANDPRAXIA;

    @JsonValue
    override fun toString() = name.toLowerCase()
}
