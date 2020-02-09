package com.microservices.component.service

import com.microservices.component.dto.Customer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {
    fun getCustomer(id: Int) : Mono<Customer>
    fun createCustomer(customer: Mono<Customer>) : Mono<Customer>
    fun deleteCustomer(id: Int): Mono<Boolean>
//    fun updateCustomer(id: Int, customer: Customer)
    fun searchCustomers(nameFilter: String) : Flux<Customer>
}