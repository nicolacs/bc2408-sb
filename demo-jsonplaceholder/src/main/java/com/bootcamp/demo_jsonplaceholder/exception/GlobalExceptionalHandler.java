package com.bootcamp.demo_jsonplaceholder.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

@RestControllerAdvice
public class GlobalExceptionalHandler {
    
    // Spring Web call -> A(Controller) call B(Service) call C ->Exception handler -> B
    @ExceptionHandler({RestClientException.class})
    public String restClientExceptionHandler(){
        return "Service Unavailable. Please try again later.";
    }

    @ExceptionHandler({JPHRestClientException.class})
    public String jphRestClientExceptionHandler(){
        return "Json Placeholder Service Unavailable. Please try again later.";
    }
}
