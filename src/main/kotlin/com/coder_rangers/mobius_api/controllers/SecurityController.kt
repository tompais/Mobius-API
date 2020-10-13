package com.coder_rangers.mobius_api.controllers

import com.coder_rangers.mobius_api.requests.SignInRequest
import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.responses.SignInResponse
import com.coder_rangers.mobius_api.services.interfaces.ISecurityService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@Validated
@RequestMapping("/security", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class SecurityController @Autowired constructor(
    private val securityService: ISecurityService
) {
    @Operation(summary = "Endpoint to sign up an user")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Patient registered successfully."),
            ApiResponse(responseCode = "400", description = "The sign up information that was provided is wrong.")
        ]
    )
    @PostMapping("/signup")
    @ResponseStatus(CREATED)
    fun signUp(@RequestBody @Valid signUpRequest: SignUpRequest) = securityService.signUp(signUpRequest)

    @Operation(summary = "Endpoint to sign in an user")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Patient logged successfully.",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = SignInResponse::class
                            )
                        )
                    )
                ]
            ),
            ApiResponse(responseCode = "400", description = "The sign in information that was provided is wrong."),
            ApiResponse(responseCode = "404", description = "The patient was not found. Check your email and password.")
        ]
    )
    @PostMapping("/signin")
    @ResponseStatus(OK)
    fun singIn(@RequestBody @Valid signInRequest: SignInRequest): SignInResponse = securityService.signIn(signInRequest)
}
