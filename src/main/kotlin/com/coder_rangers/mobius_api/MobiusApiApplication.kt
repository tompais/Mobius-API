package com.coder_rangers.mobius_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.TimeZone
import javax.annotation.PostConstruct

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.coder_rangers.mobius_api.database.repositories"])
class MobiusApiApplication {
    @PostConstruct
    fun init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }
}

fun main(args: Array<String>) {
    runApplication<MobiusApiApplication>(*args)
}
