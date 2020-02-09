package com.microservices.component.service

import com.microservices.component.dto.Customer
import com.microservices.component.repository.CustomerRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {
    override fun getCustomer(id: Int) = customerRepository.findById(id)

    override fun createCustomer(customer: Mono<Customer>) = customerRepository.create(customer)

//    override fun createCustomer(customer: Mono<Customer>) =
//        customer.flatMap {
//            if (customers[it.id] == null) {
//                customers[it.id] = it
//                it.toMono()
//            } else {
//                Mono.error(CustomerExistException("Customer ${it.id} already exist"))
//            }
//        }
//
    override fun deleteCustomer(id: Int) = customerRepository.deleteById(id).map { it.deletedCount > 0 }
//
//
//    override fun updateCustomer(id: Int, customer: Customer) {
//        deleteCustomer(id)
////        createCustomer(customer)
//    }
//
    override fun searchCustomers(nameFilter: String) = customerRepository.findCustomer(nameFilter)
}