package com.coder_rangers.mobius_api.unit.controllers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.coder_rangers.mobius_api.controllers.PingController
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class PingControllerTest {

    private val pingController = PingController()

    @Test
    fun pingTest() {
        // GIVEN
        val expectedResponse = "pong"

        // WHEN
        val actualResponse = pingController.ping()

        // THEN
        assertThat(actualResponse).isEqualTo(expectedResponse)
    }
}
