package com.coder_rangers.mobius_api.unit.services

import assertk.assertThat
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import com.coder_rangers.mobius_api.dao.interfaces.IPatientDAO
import com.coder_rangers.mobius_api.error.exceptions.DuplicatedPatientException
import com.coder_rangers.mobius_api.models.Game.Category.DRAWING
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.notifications.redis.messages.TestFinishedMessage
import com.coder_rangers.mobius_api.notifications.redis.publishers.MessagePublisher
import com.coder_rangers.mobius_api.requests.categories.GameAnswersRequest
import com.coder_rangers.mobius_api.services.implementations.PatientService
import com.coder_rangers.mobius_api.services.interfaces.IGameService
import com.coder_rangers.mobius_api.services.interfaces.IImageService
import com.coder_rangers.mobius_api.services.interfaces.IMentalTestService
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.dao.DataIntegrityViolationException

@ExtendWith(MockKExtension::class)
class PatientServiceTest {
    @RelaxedMockK
    private lateinit var patientDAO: IPatientDAO

    @RelaxedMockK
    @Suppress("UNUSED")
    private lateinit var mentalTestService: IMentalTestService

    @RelaxedMockK
    @Suppress("UNUSED")
    private lateinit var gameService: IGameService

    @RelaxedMockK
    @Suppress("UNUSED")
    private lateinit var taskResultService: ITaskResultService

    @RelaxedMockK
    @Suppress("UNUSED")
    private lateinit var testFinishedPublisher: MessagePublisher<TestFinishedMessage>

    @RelaxedMockK
    @Suppress("UNUSED")
    private lateinit var imageService: IImageService

    @InjectMockKs
    private lateinit var patientService: PatientService

    @Test
    fun playsLastCategoryTest() {
        // GIVEN
        val patient = mockk<Patient>(relaxed = true)
        val testGameAnswersRequest = mockk<GameAnswersRequest<*>>(relaxed = true) {
            every { category } returns DRAWING
            every { areTestGameAnswers } returns true
        }
        every { patientDAO.findActivePatientById(any()) } returns patient

        // WHEN
        patientService.processGameAnswers(1L, testGameAnswersRequest)

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
