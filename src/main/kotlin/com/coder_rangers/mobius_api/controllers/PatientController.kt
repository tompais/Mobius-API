package com.coder_rangers.mobius_api.controllers

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.services.interfaces.IPatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Positive

@RestController
@Validated
@RequestMapping("/patients")
class PatientController @Autowired constructor(
    private val patientService: IPatientService
) {
    @GetMapping("/{id}/mental-test/game")
    fun getMentalTestGame(@Positive @PathVariable("id") id: Long): Game = patientService.getMentalTestGame(id)
}
