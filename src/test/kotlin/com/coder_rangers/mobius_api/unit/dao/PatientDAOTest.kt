package com.coder_rangers.mobius_api.unit.dao

import com.coder_rangers.mobius_api.dao.implementations.PatientDAO
import com.coder_rangers.mobius_api.database.repositories.IPatientRepository
import com.coder_rangers.mobius_api.models.Patient
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PatientDAOTest {
    @MockK
    private lateinit var patientRepository: IPatientRepository

    @InjectMockKs
    private lateinit var patientDAO: PatientDAO

    @Test
    fun saveOrUpdateTest() {
        // GIVEN
        val patient = mockk<Patient>()
        every { patientRepository.saveAndFlush(any()) } returns patient

        // WHEN
        patientDAO.saveOrUpdate(patient)

        // THEN
        verify { patientRepository.saveAndFlush(any()) }
    }
}
