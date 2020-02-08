package com.micreservices.component

import com.micreservices.component.Customer.Telephone
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {
    override fun getCustomer(id: Int) = customers[id]?.toMono() ?: Mono.empty()

    override fun createCustomer(customer: Mono<Customer>) =
        customer.flatMap {
            if (customers[it.id] == null) {
                customers[it.id] = it
                it.toMono()
            } else {
                Mono.error(CustomerExistException("Customer ${it.id} already exist"))
            }
        }

    override fun deleteCustomer(id: Int) {
        customers.remove(id)
    } 


    override fun updateCustomer(id: Int, customer: Customer) {
        deleteCustomer(id)
//        createCustomer(customer)
    }

    override fun searchCustomers(nameFilter: String) =
        customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int, Customer>::value).toFlux()
}