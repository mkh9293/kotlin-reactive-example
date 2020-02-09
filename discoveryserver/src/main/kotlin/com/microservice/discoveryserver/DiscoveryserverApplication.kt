package com.microservice.discoveryserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableEurekaServer
@SpringBootApplication
class DiscoveryserverApplication

fun main(args: Array<String>) {
    runApplication<DiscoveryserverApplication>(*args)
}
