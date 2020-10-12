package com.coder_rangers.mobius_api.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class ResourceType {
    IMAGE,
    AUDIO,
    TEXT;

    @JsonValue
    override fun toString() = name.toLowerCase()
}
