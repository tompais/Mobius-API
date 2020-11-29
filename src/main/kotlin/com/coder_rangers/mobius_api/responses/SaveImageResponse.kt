package com.coder_rangers.mobius_api.responses

import javax.validation.constraints.NotBlank

class SaveImageResponse(
    @field:NotBlank
    val fileName: String
)
