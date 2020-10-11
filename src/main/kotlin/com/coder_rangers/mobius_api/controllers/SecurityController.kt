package com.coder_rangers.mobius_api.controllers

import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.services.interfaces.ISecurityService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.CREATED
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
    @Operation(summary = "Endpoint to sign up a user")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "User registered successfully"),
            ApiResponse(responseCode = "400", description = "The sign up information that was provided is wrong")
        ]
    )
    @PostMapping("/signup")
    @ResponseStatus(CREATED)
    fun signUp(@RequestBody @Valid signUpRequest: SignUpRequest) = securityService.signUp(signUpRequest)
}
