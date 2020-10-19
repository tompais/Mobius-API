package com.coder_rangers.mobius_api.unit.services

import assertk.assertThat
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import com.coder_rangers.mobius_api.dao.interfaces.IPatientDAO
import com.coder_rangers.mobius_api.error.exceptions.PatientNotFoundException
import com.coder_rangers.mobius_api.notifications.redis.publishers.MessagePublisher
import com.coder_rangers.mobius_api.services.implementations.SecurityService
import com.coder_rangers.mobius_api.utils.MockUtils.mockSignInRequest
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class SecurityServiceTest {
    @MockK
    private lateinit var patientDAO: IPatientDAO

    @MockK(relaxed = true)
    private lateinit var userRegisteredPublisher: MessagePublisher

    @InjectMockKs
    private lateinit var securityService: SecurityService

    @BeforeEach
    fun setUp() {
        justRun { userRegisteredPublisher.publish(any()) }
    }

    @Test
    fun patientNotFoundTest() {
        // GIVEN
        every { patientDAO.findActivePatientByEmailAndPassword(any(), any()) } returns null

        // THEN
        assertThat {
            securityService.signIn(mockSignInRequest())
        }.isFailure().isInstanceOf(PatientNotFoundException::class)
    }
}
