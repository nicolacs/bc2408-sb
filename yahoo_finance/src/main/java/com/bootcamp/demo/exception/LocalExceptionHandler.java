package com.bootcamp.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.bootcamp.demo.infra.web.ApiResp;
import com.bootcamp.demo.infra.web.GlobalExceptionalHandler;
import com.bootcamp.demo.model.dto.YahooQuoteErrorDTO;

@RestControllerAdvice
public class LocalExceptionHandler extends GlobalExceptionalHandler {
  @ExceptionHandler({StockQuoteYahooException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<YahooQuoteErrorDTO> businessExceptionHandler(StockQuoteYahooException e) {
    return ApiResp.<YahooQuoteErrorDTO>builder() //
        .fail(e) //
        .build();
  }
}