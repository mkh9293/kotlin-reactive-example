package com.micreservices.component

import java.lang.Exception

class CustomerExistException(override val message : String) : Exception(message)