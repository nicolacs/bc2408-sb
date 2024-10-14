package com.bootcamp.demo_jsonplaceholder.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

// A-> B -> C (throw)  -> B (will try catch)
//= Web Layer -> //!!!NOTE
// By default Catch Child 
@RestControllerAdvice
public class GlobalExceptionalHandler {
    
    // Spring Web call -> A(Controller) call B(Service) call C ->Exception handler -> B
    @ExceptionHandler({RestClientException.class})
    public String restClientExceptionHandler(){  //!!!可自定義 return type, 唔String, Cat OBJ都得
        return "Service Unavailable. Please try again later.";
    }

    @ExceptionHandler({JPHRestClientException.class})
    public String jphRestClientExceptionHandler(){
        return "Json Placeholder Service Unavailable. Please try again later.";
    }
}
