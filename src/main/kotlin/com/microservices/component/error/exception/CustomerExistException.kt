package com.microservices.component.error.exception

import java.lang.Exception

class CustomerExistException(override val message : String) : Exception(message)