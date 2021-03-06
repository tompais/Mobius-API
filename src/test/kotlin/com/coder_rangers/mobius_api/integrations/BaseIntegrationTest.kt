package com.coder_rangers.mobius_api.integrations

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.junit5.MockKExtension
import io.restassured.module.mockmvc.RestAssuredMockMvc
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.context.WebApplicationContext

@ExtendWith(MockKExtension::class)
@SpringBootTest
@ContextConfiguration
@TestInstance(PER_CLASS)
class BaseIntegrationTest(
    val baseUrl: String
) {
    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @MockkBean(relaxed = true)
    @Suppress("UNUSED")
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    @MockkBean(relaxed = true)
    @Suppress("UNUSED")
    private lateinit var mailSender: JavaMailSender

    @Autowired
    @Qualifier("camelCase")
    protected lateinit var mapper: ObjectMapper

    @Autowired
    protected lateinit var mockWebServer: MockWebServer

    @BeforeAll
    fun setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext)
    }

    @AfterAll
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
