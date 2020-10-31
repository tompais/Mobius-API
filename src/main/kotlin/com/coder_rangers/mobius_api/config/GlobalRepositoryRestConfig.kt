package com.coder_rangers.mobius_api.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.http.HttpMethod.DELETE
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpMethod.PUT
import org.springframework.web.cors.CorsConfiguration.ALL

@Configuration
class GlobalRepositoryRestConfig : RepositoryRestConfigurer {
    private companion object {
        private val ALLOWED_METHODS = setOf(
            GET,
            POST,
            PUT,
            DELETE
        ).map {
            it.name
        }.toTypedArray()
    }

    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration) {
        config.corsRegistry
            .addMapping("/**")
            .allowedOrigins(ALL)
            .allowedMethods(*ALLOWED_METHODS)
        
        super.configureRepositoryRestConfiguration(config)
    }
}
