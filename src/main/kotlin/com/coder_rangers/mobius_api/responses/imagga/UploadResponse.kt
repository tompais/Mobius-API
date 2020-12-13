package com.coder_rangers.mobius_api.responses.imagga

data class UploadResponse(
    val result: Result?
) {
    data class Result(
        val uploadId: String
    )
}
