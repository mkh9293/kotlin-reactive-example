package com.micreservices.component

data class Customer(val id : Int = 0, var name : String = "", var telephone: Telephone? = null) {

    data class Telephone(var countryCode: String = "", var telephoneNumber : String = "")

}