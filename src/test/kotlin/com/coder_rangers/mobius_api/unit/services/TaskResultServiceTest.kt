package com.coder_rangers.mobius_api.unit.services

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.coder_rangers.mobius_api.dao.interfaces.ITaskResultDAO
import com.coder_rangers.mobius_api.enums.DementiaLevel
import com.coder_rangers.mobius_api.enums.DementiaLevel.MILD_TO_MODERATE_DEMENTIA
import com.coder_rangers.mobius_api.enums.DementiaLevel.MODERATE_TO_SEVERE_DEMENTIA
import com.coder_rangers.mobius_api.enums.DementiaLevel.NO_DEMENTIA
import com.coder_rangers.mobius_api.enums.DementiaLevel.POSSIBLE_DEMENTIA
import com.coder_rangers.mobius_api.enums.DementiaLevel.SEVERE_DEMENTIA
import com.coder_rangers.mobius_api.services.implementations.TaskResultService
import com.coder_rangers.mobius_api.utils.TestConstant.PATIENT_ID
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

    companion object {
        @JvmStatic
        @Suppress("UNUSED")
        fun getTestResultCases() = listOf(
            Arguments.of(
                35,
                NO_DEMENTIA
            ),
            Arguments.of(
                31,
                NO_DEMENTIA
            ),
            Arguments.of(
                29,
                POSSIBLE_DEMENTIA
            ),
            Arguments.of(
                26,
                POSSIBLE_DEMENTIA
            ),
            Arguments.of(
                24,
                MILD_TO_MODERATE_DEMENTIA
            ),
            Arguments.of(
                23,
                MILD_TO_MODERATE_DEMENTIA
            ),
            Arguments.of(
                22,
                MILD_TO_MODERATE_DEMENTIA
            ),
            Arguments.of(
                20,
                MILD_TO_MODERATE_DEMENTIA
            ),
            Arguments.of(
                19,
                MODERATE_TO_SEVERE_DEMENTIA
            ),
            Arguments.of(
                17,
                MODERATE_TO_SEVERE_DEMENTIA
            ),
            Arguments.of(
                16,
                MODERATE_TO_SEVERE_DEMENTIA
            ),
            Arguments.of(
                15,
                MODERATE_TO_SEVERE_DEMENTIA
            ),
            Arguments.of(
                4,
                SEVERE_DEMENTIA
            ),
            Arguments.of(
                3,
                SEVERE_DEMENTIA
            ),
            Arguments.of(
                2,
                SEVERE_DEMENTIA
            )
        )
    }

    @ParameterizedTest
    @MethodSource("getTestResultCases")
    fun getTestResultOfPatient(expectedTestResult: Int, expectedDementiaLevel: DementiaLevel) {
        // GIVEN
        every { taskResultDAO.getTestTotalScore(any()) } returns expectedTestResult

        // WHEN
        val actualDementiaLevel = taskResultService.getPatientTestResult(PATIENT_ID).dementiaLevel
        // THEN
        verify { taskResultDAO.getTestTotalScore(any()) }

        assertThat(
            actualDementiaLevel
        ).isEqualTo(expectedDementiaLevel)
    }
}
