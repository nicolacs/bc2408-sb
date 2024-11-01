package com.bootcamp.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.infra.yahoofinance.YHRestClient;
import com.bootcamp.demo.model.dto.YahooQuoteDTO;
import com.bootcamp.demo.service.YahooFinanceService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class YahooFinanceServiceImpl implements YahooFinanceService {
  @Autowired
  private YHRestClient yahooRestClient;

  @Override
  public YahooQuoteDTO getQuote(List<String> symbols) throws JsonProcessingException {
    return yahooRestClient.getQuote(symbols);
  }
}