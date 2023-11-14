package com.kotlin.swagger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication/*(exclude=[SecurityAutoConfiguration::class])*/
class SwaggerApplication

fun main(args: Array<String>) {
	runApplication<SwaggerApplication>(*args)
}
