package com.coder_rangers.mobius_api.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.web.cors.CorsConfiguration.ALL

@Configuration
class GlobalRepositoryRestConfig : RepositoryRestConfigurer {
    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration) {
        config.corsRegistry
            .addMapping("/**")
            .allowedOrigins(ALL)
            .allowedMethods(ALL)
            .allowedHeaders(ALL)
            .allowCredentials(true)

        super.configureRepositoryRestConfiguration(config)
    }
}
