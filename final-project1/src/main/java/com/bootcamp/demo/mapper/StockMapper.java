package com.bootcamp.demo.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo.entity.StockEntity;
import com.bootcamp.demo.model.StockDTO;

@Component
public class StockMapper {
    public StockEntity map(StockDTO stock){
        return StockEntity.builder().symbol(stock.getSymbol())
          //      .regularMarketChangePercent(stock.getRegularMarketChangePercent())
                .bid(stock.getBid())
      //          .bidSize(stock.getBidSize())
                .ask(stock.getAsk())
          //      .askSize(stock.getAskSize())
                .build();
    }
}
    

