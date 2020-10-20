package com.coder_rangers.mobius_api.controllers

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.services.interfaces.IPatientService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Positive

@RestController
@Validated
@RequestMapping("/patients", produces = [APPLICATION_JSON_VALUE])
class PatientController @Autowired constructor(
    private val patientService: IPatientService
) {
    @Operation(summary = "Endpoint to get the mental test game that user has to do.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "There's a game that the user should do to finish the test.",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = Game::class
                            )
                        )
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Patient not found exception"
            ),
            ApiResponse(
                responseCode = "400",
                description = "The patient may have finished the test."
            )
        ]
    )
    @GetMapping("/{id}/mental-test/game")
    @ResponseStatus(OK)
    fun getMentalTestGame(@Positive @PathVariable("id") id: Long): Game = patientService.getMentalTestGame(id)
}
