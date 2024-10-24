package com.bootcamp.bc_forum.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;
import com.bootcamp.bc_forum.util.ApiResp;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RestClientException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse restClientExceptionHandler() {
        return ErrorResponse.builder()//
                .code(ErrorCode.RESTEMPLATE_ERROR_JSONPLACEHOLDER.getCode())//
                .message(ErrorCode.RESTEMPLATE_ERROR_JSONPLACEHOLDER.getMsg())//
                .build();
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse userNotFoundExceptionHandler() {
        return ErrorResponse.builder()
                .code(ErrorCode.USER_NOT_FOUND.getCode())
                .message(ErrorCode.USER_NOT_FOUND.getMsg())
                .build();
    }

    @ExceptionHandler({NumberFormatException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse InvalidInputExceptionHandler() {
        return ErrorResponse.builder()
                .code(ErrorCode.INVAILD_INPUT.getCode())
                .message(ErrorCode.INVAILD_INPUT.getMsg())
                .build();
    }

    @ExceptionHandler({ResTemplateErrorException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse ResTemplateErrorExceptionHandler() {
        return ErrorResponse.builder()
                .code(ErrorCode.RESTEMPLATE_ERROR_JSONPLACEHOLDER.getCode())
                .message(ErrorCode.RESTEMPLATE_ERROR_JSONPLACEHOLDER.getMsg())
                .build();
    }

    // @ExceptionHandler({SQLException.class})
    // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    // public ApiResp DataConnectErrorHandler(){
    //     return ApiResp.;
    // }

}

