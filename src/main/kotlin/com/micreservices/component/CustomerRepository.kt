package com.micreservices.component

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import javax.annotation.PostConstruct

@Repository
class CustomerRepository(private val template: ReactiveMongoTemplate) {
    companion object {
        val initialCustomers = listOf(Customer(1, "Kotlin", Customer.Telephone("+44", "123465"))
                , Customer(2, "Spring")
                , Customer(3, "Microservice", Customer.Telephone("+44", "123465")))
    }
    
    @PostConstruct
    fun initializeRepsitory() = initialCustomers.map(Customer::toMono).map(this::create).map(Mono<Customer>::subscribe)

    fun create(customter: Mono<Customer>) = template.save(customter)
}