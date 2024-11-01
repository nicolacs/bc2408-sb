package com.bootcamp.demo.exception;

import com.bootcamp.demo.infra.web.BusinessException;
import com.bootcamp.demo.infra.web.ErrorCode;
import com.bootcamp.demo.model.dto.YahooQuoteErrorDTO;

public class StockQuoteYahooException extends BusinessException {
    public StockQuoteYahooException(YahooQuoteErrorDTO quoteErrorDTO) {
      super(ErrorCode.REST_CLIENT_EX,
          quoteErrorDTO.getBody().getError().getMessage());
    }
  }