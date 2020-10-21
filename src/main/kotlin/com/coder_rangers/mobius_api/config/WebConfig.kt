package com.coder_rangers.mobius_api.config

import com.coder_rangers.mobius_api.converters.GameCategoryConverter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val gameCategoryConverter: GameCategoryConverter
) : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
    }

    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(gameCategoryConverter)
    }
}
