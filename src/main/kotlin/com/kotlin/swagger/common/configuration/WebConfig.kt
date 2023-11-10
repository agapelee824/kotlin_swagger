package com.kotlin.swagger.common.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig: WebMvcConfigurer {
    @Override
    override fun addCorsMappings(registry: CorsRegistry) {
        super.addCorsMappings(registry)

        registry.addMapping("/**")
            .allowedOrigins("http://localhost:8082")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
    }
}