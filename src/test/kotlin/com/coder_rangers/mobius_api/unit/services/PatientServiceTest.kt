package com.coder_rangers.mobius_api.unit.services

import assertk.assertThat
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import com.coder_rangers.mobius_api.dao.interfaces.IPatientDAO
import com.coder_rangers.mobius_api.error.exceptions.DuplicatedPatientException
import com.coder_rangers.mobius_api.models.Game.Category.DRAWING
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.categories.TestGameAnswersRequest
import com.coder_rangers.mobius_api.services.implementations.PatientService
import com.coder_rangers.mobius_api.services.interfaces.IGameService
import com.coder_rangers.mobius_api.services.interfaces.IMentalTestService
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.dao.DataIntegrityViolationException

@ExtendWith(MockKExtension::class)
class PatientServiceTest {
    @MockK(relaxed = true)
    private lateinit var patientDAO: IPatientDAO

    @MockK(relaxed = true)
    @Suppress("UNUSED")
    private lateinit var mentalTestService: IMentalTestService

    @MockK(relaxed = true)
    @Suppress("UNUSED")
    private lateinit var gameService: IGameService

    @MockK(relaxed = true)
    @Suppress("UNUSED")
    private lateinit var taskResultService: ITaskResultService

    @InjectMockKs
    private lateinit var patientService: PatientService

    @Test
    fun playsLastCategoryTest() {
        // GIVEN
        val patient = mockk<Patient>(relaxed = true)
        val testGameAnswersRequest = mockk<TestGameAnswersRequest<*>>(relaxed = true) {
            every { category } returns DRAWING
        }
        every { patientDAO.findActivePatientById(any()) } returns patient

        // WHEN
        patientService.processTestGameAnswers(1L, testGameAnswersRequest)

        // THEN
        verify { patientDAO.createOrUpdatePatient(any()) }
    }

    @Test
    fun duplicatedPatientTest() {
        // GIVEN
        every { patientDAO.createOrUpdatePatient(any()) } throws DataIntegrityViolationException("Duplicated patient")

        // THEN
        assertThat {
            patientService.createOrUpdatePatient(mockk(relaxed = true))
        }.isFailure().isInstanceOf(DuplicatedPatientException::class)
    }
}
