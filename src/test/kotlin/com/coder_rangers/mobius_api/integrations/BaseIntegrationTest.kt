package com.coder_rangers.mobius_api.integrations

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.junit5.MockKExtension
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
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
    private lateinit var jedisConnectionFactory: JedisConnectionFactory

    @MockkBean(relaxed = true)
    @Suppress("UNUSED")
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    @Autowired
    @Qualifier("camelCase")
    protected lateinit var mapper: ObjectMapper

    @BeforeAll
    fun setUpRestAssuredMockMvc() = RestAssuredMockMvc.webAppContextSetup(webApplicationContext)
}
