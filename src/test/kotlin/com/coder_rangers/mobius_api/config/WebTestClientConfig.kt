package com.coder_rangers.mobius_api.config

import okhttp3.mockwebserver.MockWebServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@Profile("test")
class WebTestClientConfig {
    @Bean
    fun mockWebServer() = MockWebServer()

    @Bean
    @Autowired
    fun webTestClient(mockWebServer: MockWebServer): WebClient = WebClient.builder()
        .baseUrl("http://localhost:${mockWebServer.port}")
        .build()
}
