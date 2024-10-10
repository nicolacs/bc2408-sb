package com.bootcamp.bc_calculator.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

@RestControllerAdvice
public class GlobalExceptionalHandler {
    @ExceptionHandler({RestClientException.class})
    public String restClientExceptionHandler(){
        return "Invaild input, please try again.";
    }
    
}
