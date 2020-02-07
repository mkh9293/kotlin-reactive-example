package com.micreservices.component

import org.springframework.web.bind.annotation.*

@RestController
class CustomerController(private val customerService : CustomerService ) {

    @RequestMapping(path= ["/customer/{id}"], method=arrayOf(RequestMethod.GET))
    fun getCustomer(@PathVariable id : Int) = customerService.getCustomer(id)

    @RequestMapping(path= ["/customers"], method= arrayOf(RequestMethod.GET))
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter : String) =
            customerService.searchCustomers(nameFilter)

    @RequestMapping(path= ["/customer"], method=arrayOf(RequestMethod.POST))
    fun createCustomer(@RequestBody customer: Customer) { customerService.createCustomer(customer) }

    @RequestMapping(path= ["/customer/{id}"], method=arrayOf(RequestMethod.DELETE))
    fun deleteCustomer(@PathVariable id : Int) = customerService.deleteCustomer(id)

    @RequestMapping(path= ["/customer/{id}"], method=arrayOf(RequestMethod.PUT))
    fun updateCustomer(@PathVariable id : Int, @RequestBody customer: Customer) {
        customerService.updateCustomer(id, customer)
    }
}