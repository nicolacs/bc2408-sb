package com.bootcamp.demo.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo.entity.StockEntity;
import com.bootcamp.demo.model.Stock;

@Component
public class StockMapper {
  public Stock map(StockEntity entity) {
    return Stock.builder() //
        .id(entity.getId()) //
        .symbol(entity.getSymbol()) //
        .build();
  }
}