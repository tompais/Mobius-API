package com.coder_rangers.mobius_api.unit.services

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.coder_rangers.mobius_api.dao.interfaces.ITaskResultDAO
import com.coder_rangers.mobius_api.responses.PatientTestResult.DementiaLevel
import com.coder_rangers.mobius_api.responses.PatientTestResult.DementiaLevel.MILD_TO_MODERATE_DEMENTIA
import com.coder_rangers.mobius_api.responses.PatientTestResult.DementiaLevel.MODERATE_TO_SEVERE_DEMENTIA
import com.coder_rangers.mobius_api.responses.PatientTestResult.DementiaLevel.NO_DEMENTIA
import com.coder_rangers.mobius_api.responses.PatientTestResult.DementiaLevel.POSSIBLE_DEMENTIA
import com.coder_rangers.mobius_api.responses.PatientTestResult.DementiaLevel.SEVERE_DEMENTIA
import com.coder_rangers.mobius_api.services.implementations.TaskResultService
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_ID
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@ExtendWith(MockKExtension::class)
class TaskResultServiceTest {
    @MockK
    private lateinit var taskResultDAO: ITaskResultDAO

    @InjectMockKs
    private lateinit var taskResultService: TaskResultService

    private companion object {
        @JvmStatic
        @Suppress("UNUSED")
        fun getTestResultCases() = listOf(
            Arguments.of(
                28,
                NO_DEMENTIA
            ),
            Arguments.of(
                25,
                POSSIBLE_DEMENTIA
            ),
            Arguments.of(
                15,
                MILD_TO_MODERATE_DEMENTIA
            ),
            Arguments.of(
                7,
                MODERATE_TO_SEVERE_DEMENTIA
            ),
            Arguments.of(
                4,
                SEVERE_DEMENTIA
            )
        )
    }

    @ParameterizedTest
    @MethodSource("getTestResultCases")
    fun getTestResultOfPatient(expectedTestResult: Int, expectedDementiaLevel: DementiaLevel) {
        // GIVEN
        every { taskResultDAO.getPatientTestResult(any()) } returns expectedTestResult

        // WHEN
        val actualDementiaLevel = taskResultService.getPatientTestResult(PATIENT_ID).result
        // THEN
        verify { taskResultDAO.getPatientTestResult(any()) }

        assertThat(
            actualDementiaLevel
        ).isEqualTo(expectedDementiaLevel)
    }
}
