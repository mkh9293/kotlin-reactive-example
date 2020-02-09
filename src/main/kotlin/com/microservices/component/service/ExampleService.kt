package com.microservices.component.service

import com.microservices.component.service.ServiceInterface
import org.springframework.beans.factory.annotation.Value

class ExampleService : ServiceInterface {

    @Value(value="\${service.message.text}")
    private lateinit var text: String

    override fun getHello(name : String) = "$text $name"
}