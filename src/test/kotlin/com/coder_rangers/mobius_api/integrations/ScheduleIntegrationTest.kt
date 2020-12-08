package com.coder_rangers.mobius_api.integrations

import com.coder_rangers.mobius_api.database.repositories.IPatientRepository
import com.coder_rangers.mobius_api.models.Answer.Type.EXPECTED
import com.coder_rangers.mobius_api.models.Game.Category.FIXATION
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.Task
import com.coder_rangers.mobius_api.utils.MockUtils.mockPatient
import com.ninjasquad.springmockk.SpykBean
import io.mockk.every
import io.mockk.mockk
import io.restassured.module.mockmvc.RestAssuredMockMvc.`when`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.http.HttpStatus.NO_CONTENT
import java.time.LocalDate

class ScheduleIntegrationTest : BaseIntegrationTest("/schedules") {
    @SpykBean
    private lateinit var patientRepository: IPatientRepository

    companion object {
        @JvmStatic
        @Suppress("UNUSED")
        fun sendWeeklyReportsCases() =
            listOf(
                emptySet(),
                setOf(
                    mockPatient()
                ),
                setOf(
                    mockPatient(
                        taskResults = mutableSetOf(
                            getMockTaskResultByWeeksToSubtract(1L)
                        )
                    )
                ),
                setOf(
                    mockPatient(
                        taskResults = mutableSetOf(
                            getMockTaskResultByWeeksToSubtract(1L),
                            getMockTaskResultByWeeksToSubtract(2L)
                        )
                    )
                )
            )

        private fun getMockTaskResultByWeeksToSubtract(weeksToSubtract: Long): Task.Result = mockk(relaxed = true) {
            every { playedDate } returns LocalDate.now().minusWeeks(weeksToSubtract)
            every { score } returns 5
            every { task } returns mockk(relaxed = true) {
                every { answers } returns setOf(
                    mockk {
                        every { type } returns EXPECTED
                    }
                )

                every { game } returns mockk {
                    every { category } returns FIXATION
                    every { isTestGame } returns false
                }
            }
        }
    }

    @ParameterizedTest
    @MethodSource("sendWeeklyReportsCases")
    fun sendWeeklyReportsTest(patients: Set<Patient>) {
        every { patientRepository.findAllByStatusAndTestStatus(any(), any()) } returns patients

        `when`()
            .post("$baseUrl/patients/weekly-report")
            .then()
            .assertThat()
            .status(NO_CONTENT)
    }
}
