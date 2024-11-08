package com.bootcamp.demo.service;

import java.util.List;
import com.bootcamp.demo.entity.StockEntity;
import com.bootcamp.demo.model.Stock;
import com.bootcamp.demo.model.dto.APIDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StockService {
  List<StockEntity> saveAll(List<StockEntity> entities);
  List<StockEntity> findAll();
  List<Stock> findAllWithCache() throws JsonProcessingException;
  StockEntity findBySymbol(String Symbol);
  String getMaxDay(String symbol);
  APIDTO get5minData(String symbol);
}