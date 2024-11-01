package com.bootcamp.demo.config;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.entity.StockEntity;
import com.bootcamp.demo.entity.StockPriceEntity;
import com.bootcamp.demo.mapper.StockPriceMapper;
import com.bootcamp.demo.model.Stock;
import com.bootcamp.demo.model.dto.YahooQuoteDTO;
import com.bootcamp.demo.repository.StockPriceRepository;
import com.bootcamp.demo.service.StockService;
import com.bootcamp.demo.service.YahooFinanceService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class StockQuoteYahooScheduler {
  @Autowired
  private YahooFinanceService yahooFinanceService;
  @Autowired
  private StockPriceMapper stockPriceMapper;
  @Autowired
  private StockService stockService;
  @Autowired
  private StockPriceRepository stockPriceRepository;
 
  // ! Every 15 minutes
  @Scheduled(fixedRate = 300_000)
  // @Scheduled(cron = "0 */5 9-16 * * MON-FRI")
  public void stockQuote() throws JsonProcessingException {
    List<Stock> stocks = this.stockService.findAllWithCache();
    if (stocks == null || stocks.size() == 0)
      return;
    List<String> symbols = stocks.stream() //
        .map(s -> s.getSymbol()) //
        .collect(Collectors.toList());
    // System.out.println("symbols=" + symbols);
    YahooQuoteDTO quoteDTO = this.yahooFinanceService.getQuote(symbols);
    quoteDTO.getBody().getResults().forEach(s -> {
      StockEntity stockEntity = this.stockService.findBySymbol(s.getSymbol());
      StockPriceEntity stockPriceEntity = this.stockPriceMapper.map(s);
      stockPriceEntity.setStock(stockEntity);
      stockPriceEntity.setSymbol(stockEntity.getSymbol());
      stockPriceEntity.setTranType("5MIN");
      this.stockPriceRepository.save(stockPriceEntity);
    });
  }
}