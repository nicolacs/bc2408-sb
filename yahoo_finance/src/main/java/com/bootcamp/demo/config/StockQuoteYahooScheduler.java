package com.bootcamp.demo.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
  @Autowired
  private RedisTemplate<String, String> redisTemplate;

   @Scheduled(fixedRate = 120_000)
    // @Scheduled(cron = "0 34,39,44,49,54,59 9 * * MON-FRI",
    //     zone = "Asia/Hong_Kong")
    // @Scheduled(cron = "0 4,9,14,19,24,29,34,39,44,49,54,59 10-15 * * MON-FRI",
    //    zone = "Asia/Hong_Kong")
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
      System.out.println("stockPriceEntity**********" +stockPriceEntity);

    // Save into Redis
      String redisKey = stockEntity.getSymbol();
      LocalDate systemDate = stockPriceEntity.getMarketDateTime().toLocalDate();
      String formattedDate = systemDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
      redisTemplate.opsForValue().set(redisKey , formattedDate, 8, TimeUnit.HOURS);
    });      
  }
}
