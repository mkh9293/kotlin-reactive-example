package com.microservices.component.controller

import com.microservices.component.dto.Customer
import com.microservices.component.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class CustomerController(private val customerService : CustomerService) {

    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id : Int) : ResponseEntity<Mono<Customer>> {
        val customer = customerService.getCustomer(id)
        return ResponseEntity(customer, HttpStatus.OK)
    }

    @RequestMapping(path= ["/customers"], method= arrayOf(RequestMethod.GET))
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter : String) =
            customerService.searchCustomers(nameFilter)

    @PostMapping("/customer")
    fun createCustomer(@RequestBody customer: Mono<Customer>) =
        ResponseEntity(customerService.createCustomer(customer), HttpStatus.CREATED)


    @DeleteMapping("/customer/{id}")
    fun deleteCustomer(@PathVariable id : Int) : ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if(customerService.getCustomer(id) != null) {
            customerService.deleteCustomer(id)
            status = HttpStatus.OK
        }

        return ResponseEntity(Unit, status)
    }

//    @PutMapping("/customer/{id}")
//    fun updateCustomer(@PathVariable id : Int, @RequestBody customer: Customer) : ResponseEntity<Unit> {
//        var status = HttpStatus.NOT_FOUND
//        if(customerService.getCustomer(id) != null) {
//            customerService.updateCustomer(id, customer)
//            status = HttpStatus.ACCEPTED
//        }
//        return ResponseEntity(Unit, status)
//    }

    @GetMapping("/json")
    fun getJson() = ComplexObject(SimpleObject("more", "complex"))
}

data class SimpleObject (val name : String, val zone : String)

data class ComplexObject(val object1 : SimpleObject? = null)