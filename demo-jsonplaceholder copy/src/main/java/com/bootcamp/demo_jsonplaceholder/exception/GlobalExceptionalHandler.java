package com.bootcamp.demo_jsonplaceholder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

// ! Java: A -> B -> C, if C throw, the state will be back to B.  B (will try catch)
// ! Spring: Web Layer -> Controller -> Service (throw) -> Exception Hanlder -> Web Layer

// ! Advantages: Appropriate for General Exception Handling (i.e NPE)
// By default Catch Child  Exception first, then parent exception
@RestControllerAdvice // Inspector (Between methods)
public class GlobalExceptionalHandler {
    
    // Spring Web call -> A(Controller) call B(Service) call C ->Exception handler -> B
    @ExceptionHandler({RestClientException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400 http status code
    public String restClientExceptionHandler(){  //!!!可自定義 return type, 唔String, Cat OBJ都得
        return "Service Unavailable. Please try again later.";
    }

    @ExceptionHandler({JPHRestClientException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String jphRestClientExceptionHandler(){
        return "Json Placeholder Service Unavailable. Please try again later.";
    }
}
