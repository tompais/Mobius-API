package com.coder_rangers.mobius_api.unit.services

import assertk.assertThat
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import com.coder_rangers.mobius_api.components.interfaces.IJWTGenerator
import com.coder_rangers.mobius_api.error.exceptions.PatientNotFoundException
import com.coder_rangers.mobius_api.services.implementations.SecurityService
import com.coder_rangers.mobius_api.services.interfaces.IGuardianService
import com.coder_rangers.mobius_api.services.interfaces.IPatientService
import com.coder_rangers.mobius_api.utils.MockUtils.mockSignInRequest
import com.coder_rangers.mobius_api.utils.TestConstant.TOKEN
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class SecurityServiceTest {
    @MockK
    private lateinit var patientService: IPatientService

    @MockK
    @Suppress("UNUSED")
    private lateinit var guardianService: IGuardianService

    @MockK
    private lateinit var jwtGenerator: IJWTGenerator

    @InjectMockKs
    private lateinit var securityService: SecurityService

    @Test
    fun patientNotFoundTest() {
        // GIVEN
        every { jwtGenerator.generateJWT(any()) } returns TOKEN
        every { patientService.getActivePatientByEmailAndPassword(any(), any()) } throws PatientNotFoundException()

        // THEN
        assertThat {
            securityService.signIn(mockSignInRequest())
        }.isFailure().isInstanceOf(PatientNotFoundException::class)
    }
}
