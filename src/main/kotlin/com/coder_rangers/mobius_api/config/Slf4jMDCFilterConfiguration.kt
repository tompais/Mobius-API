package com.coder_rangers.mobius_api.config

import com.coder_rangers.mobius_api.filters.Slf4jMDCFilter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "config.slf4jfilter")
class Slf4jMDCFilterConfiguration {
    companion object {
        private const val DEFAULT_RESPONSE_TOKEN_HEADER = "Response_Token"
        const val DEFAULT_MDC_UUID_TOKEN_KEY = "Slf4jMDCFilter.UUID"
    }

    @Bean
    fun servletRegistrationBean(): FilterRegistrationBean<*>? {
        return FilterRegistrationBean(
            Slf4jMDCFilter(DEFAULT_RESPONSE_TOKEN_HEADER, DEFAULT_MDC_UUID_TOKEN_KEY)
        ).apply {
            order = 2
        }
    }
}
