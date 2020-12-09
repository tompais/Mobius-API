package com.coder_rangers.mobius_api.controllers

import com.coder_rangers.mobius_api.services.interfaces.IScheduleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/schedules")
class ScheduleController @Autowired constructor(
    private val scheduleService: IScheduleService
) {
    @PostMapping("/patients/weekly-report")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Endpoint to send a weekly report of patients")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "The reports were sent successfully."
            )
        ]
    )
    @Scheduled(cron = "0 0 0 * * 1")
    fun sendWeeklyReports() {
        scheduleService.sendWeeklyReports()
    }
}
