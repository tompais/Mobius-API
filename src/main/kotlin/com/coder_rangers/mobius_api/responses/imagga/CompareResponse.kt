package com.coder_rangers.mobius_api.responses.imagga

data class CompareResponse(
    val result: Result?
) {
    data class Result(
        val distance: Double
    )
}
