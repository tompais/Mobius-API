package com.coder_rangers.mobius_api.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfiguration.ALL
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.OncePerRequestFilter

@Profile("prod")
@Configuration
@EnableWebSecurity
class SecurityConfig @Autowired constructor(
    @Qualifier("JWTAuthorizationFilter")
    private val jwtAuthorizationFilter: OncePerRequestFilter,
) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.cors()
            .and()
            .csrf().disable()
            .addFilterAfter(
                jwtAuthorizationFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )
            .authorizeRequests()
            .antMatchers(
                "/",
                "/ping",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/security/**",
                "/internal/**",
                "/images/**",
                "/audios/**",
                "/texts/**",
                "/schedules/**"
            )
            .permitAll()
            .anyRequest()
            .authenticated()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource = CorsConfiguration().apply {
        allowedOrigins = listOf(ALL)
        allowedMethods = listOf(ALL)
        allowedHeaders = listOf(ALL)
        allowCredentials = true
    }.let { corsConfiguration ->
        UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration(
                "/**",
                corsConfiguration
            )
        }
    }
}
