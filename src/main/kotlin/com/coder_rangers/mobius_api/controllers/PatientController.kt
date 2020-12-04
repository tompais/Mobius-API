package com.coder_rangers.mobius_api.controllers

import com.coder_rangers.mobius_api.error.exceptions.ExclusiveTestCategoryException
import com.coder_rangers.mobius_api.error.exceptions.FinishedTestException
import com.coder_rangers.mobius_api.error.exceptions.GameNotFoundException
import com.coder_rangers.mobius_api.error.exceptions.NotAllTasksProvidedException
import com.coder_rangers.mobius_api.error.exceptions.PatientNotFoundException
import com.coder_rangers.mobius_api.error.exceptions.TestNotFinishedException
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.categories.GameAnswersRequest
import com.coder_rangers.mobius_api.responses.PatientTestResult
import com.coder_rangers.mobius_api.services.interfaces.IPatientService
import com.coder_rangers.mobius_api.view.models.HomeViewModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Positive

@RestController
@Validated
@RequestMapping("/patients", produces = [APPLICATION_JSON_VALUE])
class PatientController @Autowired constructor(
    private val patientService: IPatientService
) {
    @GetMapping("/{id}/game")
    @ResponseStatus(OK)
    @Operation(summary = "Endpoint to get the game that user has to do.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "The game was retrieved successfully.",
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
                description = "The patient or the game were not found.",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = PatientNotFoundException::class
                            )
                        )
                    ),
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = GameNotFoundException::class
                            )
                        )
                    )
                ]
            ),
            ApiResponse(
                responseCode = "400",
                description = "The patient may or may not have finished the test or the category provided is exclusive for the test.",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = FinishedTestException::class
                            )
                        )
                    ),
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = TestNotFinishedException::class
                            )
                        )
                    ),
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = ExclusiveTestCategoryException::class
                            )
                        )
                    )
                ]
            )
        ]
    )
    fun getGame(
        @Positive
        @PathVariable("id")
        id: Long,

        @RequestParam("next-game-category")
        nextGameCategory: Game.Category,

        @RequestParam("is-test-game")
        isTestGame: Boolean
    ): Game = patientService.getGame(id, nextGameCategory, isTestGame)

    @PostMapping("/{id}/game/answers", consumes = [APPLICATION_JSON_VALUE])
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Endpoint to process and save the answers of the game")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "Game answers processed and registered successfully."
            ),
            ApiResponse(
                responseCode = "400",
                description = "The game answers information that was provided is wrong or the patient may or may not have finished the test.",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = NotAllTasksProvidedException::class
                            )
                        )
                    ),
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = FinishedTestException::class
                            )
                        )
                    ),
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = TestNotFinishedException::class
                            )
                        )
                    )
                ]
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
                    ),
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = GameNotFoundException::class
                            )
                        )
                    )
                ]
            )
        ]
    )
    fun processTestGameAnswers(
        @PathVariable("id")
        @Positive
        id: Long,

        @RequestBody
        @Valid
        gameAnswersRequest: GameAnswersRequest<*>
    ) = patientService.processGameAnswers(id, gameAnswersRequest)

    @GetMapping("/{id}/mental-test/result")
    @ResponseStatus(OK)
    @Operation(summary = "Endpoint to get the test result")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Test result retrieved successfully.",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = PatientTestResult::class
                            )
                        )
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "The patient was not found.",
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
            ),
            ApiResponse(
                responseCode = "400",
                description = "The test was not finished.",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = TestNotFinishedException::class
                            )
                        )
                    )
                ]
            )
        ]
    )
    fun getTestResult(
        @PathVariable("id")
        @Positive
        id: Long
    ): PatientTestResult = patientService.getTestResult(id)

    @GetMapping("{id}/home")
    @ResponseStatus(OK)
    @Operation(summary = "Endpoint to get the list of categories and the recommended category")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "List of categories and recommended category were retrieved successfully.",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = HomeViewModel::class
                            )
                        )
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "The patient was not found.",
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
    fun getHome(
        @Positive
        @PathVariable("id")
        id: Long
    ) = patientService.getHome(id)
}
