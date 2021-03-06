package com.microservices.component.handler

import com.microservices.component.dto.Customer
import com.microservices.component.service.CustomerService
import com.microservices.component.error.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.onErrorResume

@Component
class CustomerHandler(private val customerService: CustomerService) {

    fun get(serverRequest: ServerRequest) =
            customerService.getCustomer(serverRequest.pathVariable("id").toInt())
                    .flatMap { ok().body(fromObject(it)) }
                    .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

//    fun search(serverRequest: ServerRequest) =
//            ok().body(customerService.searchCustomers(serverRequest.queryParam("nameFilter").orElse("")))

    fun create(serverRequest: ServerRequest) =
            customerService.createCustomer(serverRequest.bodyToMono())
                    .flatMap {
                        status(HttpStatus.CREATED).body(fromObject(it))
                    }
                    .onErrorResume(Exception::class) {
                        badRequest().body(fromObject(ErrorResponse("error creating customer", it.message
                                ?: "error")))
                    }

    fun delete(serverRequest: ServerRequest) =
            customerService.deleteCustomer(serverRequest.pathVariable("id").toInt()).
                    flatMap {
                        if (it) ok().build()
                        else status(HttpStatus.NOT_FOUND).build()
                    }

    fun search(serverRequest: ServerRequest) =
            ok().body(customerService.searchCustomers(serverRequest.queryParam("nameFilter").orElse("")), Customer::class.java)

}