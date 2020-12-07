package com.coder_rangers.mobius_api.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.templatemode.TemplateMode.HTML
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import org.thymeleaf.templateresolver.ITemplateResolver

@Configuration
class ThymeleafConfig {
    @Bean
    fun thymeleafTemplateResolver(): ITemplateResolver = ClassLoaderTemplateResolver().apply {
        prefix = "email/templates/"
        suffix = ".html"
        templateMode = HTML
        characterEncoding = "UTF-8"
    }

    @Bean
    fun thymeleafTemplateEngine(
        @Qualifier("thymeleafTemplateResolver")
        templateResolver: ITemplateResolver
    ): SpringTemplateEngine = SpringTemplateEngine().apply {
        setTemplateResolver(templateResolver)
    }
}
