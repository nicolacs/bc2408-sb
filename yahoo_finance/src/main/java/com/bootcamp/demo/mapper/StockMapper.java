package com.bootcamp.demo.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.entity.StockEntity;
import com.bootcamp.demo.entity.StockPriceEntity;
import com.bootcamp.demo.model.Stock;
import com.bootcamp.demo.model.dto.APIDTO;

@Component
public class StockMapper {
  public Stock map(StockEntity entity) {
    return Stock.builder() //
        .id(entity.getId()) //
        .symbol(entity.getSymbol()) //
        .build();
  }

  public APIDTO.Data map(StockPriceEntity entity) {
    return APIDTO.Data.builder()//
        .marketTime(entity.getMarketDateTime().toString())//
        .regularMarketChangePercent(entity.getMarketPriceChangePercent())//
        .regularMarketPrice(entity.getMarketPrice())//
        .regularMarketUnix(entity.getMarketUnixTime())//
        .symbol(entity.getSymbol())//
        .build();


  }
}
