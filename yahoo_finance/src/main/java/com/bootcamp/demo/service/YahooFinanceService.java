package com.bootcamp.demo.service;

import java.util.List;
import com.bootcamp.demo.model.dto.YahooQuoteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface YahooFinanceService {
    YahooQuoteDTO getQuote(List<String> symbols) throws JsonProcessingException;
  }