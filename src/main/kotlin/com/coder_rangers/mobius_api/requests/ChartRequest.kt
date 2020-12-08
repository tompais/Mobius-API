package com.coder_rangers.mobius_api.requests

import com.fasterxml.jackson.annotation.JsonValue

class ChartRequest(
    val type: Type,
    val data: Data
) {
    enum class Type {
        PIE,
        BAR;

        @JsonValue
        override fun toString(): String {
            return name.toLowerCase()
        }
    }

    class Data(
        val labels: Set<String>,
        val datasets: MutableList<Dataset>
    ) {
        class Dataset(
            val data: List<Int>,
            val label: String? = null
        )
    }
}
