package com.coder_rangers.mobius_api.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@Profile("prod")
class WebClientConfig @Autowired constructor(
    @Qualifier("snakeCase")
    private val mapper: ObjectMapper,

    @Value("\${imagga.authorization_value}")
    private val imaggaAuthValue: String
) {
    @Bean
    fun imaggaWebClient(): WebClient = WebClient
        .builder()
        .baseUrl("https://api.imagga.com/v2")
        .codecs { clientDefaultCodecsConfigurer ->
            clientDefaultCodecsConfigurer.defaultCodecs()
                .jackson2JsonEncoder(
                    Jackson2JsonEncoder(
                        mapper,
                        APPLICATION_JSON
                    )
                )
            clientDefaultCodecsConfigurer.defaultCodecs()
                .jackson2JsonDecoder(
                    Jackson2JsonDecoder(
                        mapper,
                        APPLICATION_JSON
                    )
                )
        }
        .defaultHeader(
            AUTHORIZATION,
            "Basic $imaggaAuthValue"
        )
        .build()
}
