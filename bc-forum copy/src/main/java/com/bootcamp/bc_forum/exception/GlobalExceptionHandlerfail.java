package com.bootcamp.bc_forum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerfail {

    // @ExceptionHandler(value = Exception.class)
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    // public ResponseEntity<ErrorResponse> handleUserNotFoundException(
    //         Exception ex) {
    //     return buildResponse(Code.User_not_found);
    // }

    // // @ExceptionHandler(value = Exception.class)
    // // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // // public ResponseEntity<ErrorResponse> handleInvalidInputException(
    // //         Exception ex) {
    // //     return buildResponse(Code.Invalid_Input);
    // // }

    // // @ExceptionHandler(value = Exception.class)
    // // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // // public ResponseEntity<ErrorResponse> handleResTemplateErrorJsonPlaceHolderException(
    // //         Exception ex) {
    // //     return buildResponse(Code.ResTemplate_Error_JsonPlaceHolder);
    // // }

    // private ResponseEntity<ErrorResponse> buildResponse(Code code) {
    //     ErrorResponse errorResponse =
    //             new ErrorResponse(code.getCode(), code.getMsg());
    //     return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(
    //             code.getCode() == 1 ? 404 : (code.getCode() == 2 ? 400 : 500)));
    // }

    // public static class ErrorResponse {
    //     private final Integer code;
    //     private final String message;

    //     public ErrorResponse(Integer code, String message) {
    //         this.code = code;
    //         this.message = message;
    //     }

    //     public Integer getCode() {
    //         return code;
    //     }

    //     public String getMessage() {
    //         return message;
    //     }
    // }
}
