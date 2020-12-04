package com.coder_rangers.mobius_api.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER
import io.swagger.v3.oas.models.security.SecurityScheme.Type.APIKEY
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAPIConfig {
    @Bean
    fun customOpenAPI(): OpenAPI? {
        return OpenAPI()
            .components(
                Components().addSecuritySchemes(
                    "token",
                    SecurityScheme().type(APIKEY).`in`(HEADER).name("Authorization")
                )
            )
    }
}
