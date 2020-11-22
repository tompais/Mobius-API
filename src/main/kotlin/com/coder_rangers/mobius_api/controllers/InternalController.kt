package com.coder_rangers.mobius_api.controllers

import com.coder_rangers.mobius_api.error.exceptions.PatientNotFoundException
import com.coder_rangers.mobius_api.services.interfaces.IPatientService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Positive

@RestController
@Validated
@RequestMapping("/internal")
class InternalController @Autowired constructor(
    private val patientService: IPatientService
) {
    @PostMapping("/patients/{patientId}/test-progress/clean")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Endpoint to clean the patient test progress")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "The patient test progress was cleaned successfully."
            ),
            ApiResponse(
                responseCode = "404",
                description = "The patient or the game were not found.",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = PatientNotFoundException::class
                            )
                        )
                    )
                ]
            )
        ]
    )
    fun cleanPatientTestProgress(
        @Positive
        @PathVariable("patientId")
        patientId: Long
    ) = patientService.cleanTestProgress(patientId)
}
