package com.bootcamp.demo.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.entity.StockPriceEntity;
import com.bootcamp.demo.model.dto.YahooQuoteDTO;

@Component
public class StockPriceMapper {
  public StockPriceEntity map(YahooQuoteDTO.QuoteBody.QuoteResult quotePrice) {
    LocalDateTime quoteDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochSecond(quotePrice.getRegularMarketTime()),
        ZoneId.systemDefault());
        
    return StockPriceEntity.builder()
        .symbol(quotePrice.getSymbol())
        .tranDatetime(LocalDateTime.now()) //
        .marketUnixTime(quotePrice.getRegularMarketTime()) //
        .marketDateTime(quoteDateTime) //
        .marketPrice(quotePrice.getRegularMarketPrice()) //
        .marketPriceChangePercent(quotePrice.getRegularMarketChangePercent()) //
        .ask(quotePrice.getAsk()) //
        .askSize(quotePrice.getAskSize()) //
        .bid(quotePrice.getBid()) //
        .bidSize(quotePrice.getBidSize()) //
        .build();
  }
}