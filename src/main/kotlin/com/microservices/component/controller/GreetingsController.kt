package com.microservices.component.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RefreshScope
@RestController
class GreetingsController {

    @Value("\${microservice.example.greetings}")
    private lateinit var greetings: String

    @GetMapping("/greetings")
    fun greetings() = "hello from a docker1"
}