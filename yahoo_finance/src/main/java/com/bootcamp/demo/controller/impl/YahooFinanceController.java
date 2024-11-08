package com.bootcamp.demo.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.controller.YahooFinanceOperation;
import com.bootcamp.demo.model.dto.APIDTO;
import com.bootcamp.demo.model.dto.YahooQuoteDTO;
import com.bootcamp.demo.service.StockService;
import com.bootcamp.demo.service.YahooFinanceService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/v1")
public class YahooFinanceController implements YahooFinanceOperation {

  @Autowired
  private YahooFinanceService yahooFinanceService;

  @Autowired
  private StockService stockService;

  @Override
  public YahooQuoteDTO getQuote(List<String> symbols) throws JsonProcessingException {
    return yahooFinanceService.getQuote(symbols);
  }

  @Override
  public APIDTO get5minData(String symbols) throws JsonProcessingException{
    return stockService.get5minData(symbols);
  
  }
}