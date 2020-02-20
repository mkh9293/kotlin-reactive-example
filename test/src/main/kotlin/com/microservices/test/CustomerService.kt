package com.microservices.test

interface CustomerService {
    fun getCustomer(id: Int) : Customer?
    fun getAllCustomers() : List<Customer>
}