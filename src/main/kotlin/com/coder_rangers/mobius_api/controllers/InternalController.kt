package com.coder_rangers.mobius_api.controllers

import com.coder_rangers.mobius_api.services.interfaces.IPatientService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.OK
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
    @Operation(summary = "Endpoint to clean the patient test progress")
    @GetMapping("/{patientId}/clean")
    @ResponseStatus(OK)
    fun cleanPatientTestProgress(
        @Positive
        @PathVariable("patientId")
        patientId: Long
    ) = patientService.cleanTestProgress(patientId)
}
