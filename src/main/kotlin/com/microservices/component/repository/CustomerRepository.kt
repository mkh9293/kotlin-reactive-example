package com.microservices.component.repository

import com.microservices.component.dto.Customer
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.findById
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.mongodb.core.remove
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

    fun findById(id: Int) = template.findById<Customer>(id)

    fun deleteById(id: Int) = template.remove<Customer>(Query(where("_id").isEqualTo(id)))

    fun findCustomer(nameFilter: String) = template.find<Customer>(Query(where("name").regex(".*$nameFilter.*", "i")))
}