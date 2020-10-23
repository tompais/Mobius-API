package com.coder_rangers.mobius_api.unit.services

import com.coder_rangers.mobius_api.dao.interfaces.IPatientDAO
import com.coder_rangers.mobius_api.models.Game.Category.LANGUAGE_AND_PRAXIS
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.categories.TestGameAnswersRequest
import com.coder_rangers.mobius_api.services.implementations.PatientService
import com.coder_rangers.mobius_api.services.interfaces.IMentalTestService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PatientServiceTest {
    @MockK(relaxed = true)
    private lateinit var patientDAO: IPatientDAO

    @MockK(relaxed = true)
    @Suppress("UNUSED")
    private lateinit var mentalTestService: IMentalTestService

    @InjectMockKs
    private lateinit var patientService: PatientService

    @Test
    fun playsLastCategoryTest() {
        // GIVEN
        val patient = mockk<Patient>(relaxed = true)
        val testGameAnswersRequest = mockk<TestGameAnswersRequest<*>>(relaxed = true) {
            every { category } returns LANGUAGE_AND_PRAXIS
        }
        every { patientDAO.findActivePatientById(any()) } returns patient

        // WHEN
        patientService.processTestGameAnswers(1L, testGameAnswersRequest)

        // THEN
        verify { patientDAO.saveOrUpdate(any()) }
    }
}
