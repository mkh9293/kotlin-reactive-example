package com.micreservices.component

import org.springframework.web.bind.annotation.ControllerAdvice
//import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ErrorHandler {

//    @ExceptionHandler(JsonParseException::class)
//    fun jsonParseExceptionHandler(servletRequest: HttpServletRequest, exception: Exception) :
//            ResponseEntity<ErrorResponse> {
//        return ResponseEntity(ErrorResponse("JSON Error", exception.message ?: "invalid json"), HttpStatus.BAD_REQUEST)
//    }
//
//    @ExceptionHandler(CustomerNotFoundException::class)
//    fun customerNotFoundExceptionHandler(servletRequest: HttpServletRequest, exception: Exception) :
//            ResponseEntity<ErrorResponse> {
//        return ResponseEntity(ErrorResponse("Customer Not Found", exception.message!!), HttpStatus.NOT_FOUND)
//    }


}