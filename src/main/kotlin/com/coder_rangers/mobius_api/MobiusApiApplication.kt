package com.coder_rangers.mobius_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.retry.annotation.EnableRetry
import java.util.TimeZone
import javax.annotation.PostConstruct

@SpringBootApplication
@EnableRetry
@EnableJpaRepositories(basePackages = ["com.coder_rangers.mobius_api.database.repositories"])
class MobiusApiApplication {
    @PostConstruct
    fun init() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"))
    }
}

fun main(args: Array<String>) {
    runApplication<MobiusApiApplication>(*args)
}
