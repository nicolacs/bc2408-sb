package com.bootcamp.demo.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.entity.StockEntity;
import com.bootcamp.demo.infra.web.BusinessException;
import com.bootcamp.demo.infra.web.ErrorCode;
import com.bootcamp.demo.mapper.StockMapper;
import com.bootcamp.demo.model.Stock;
import com.bootcamp.demo.model.dto.APIDTO;
import com.bootcamp.demo.repository.StockPriceRepository;
import com.bootcamp.demo.repository.StockRepository;
import com.bootcamp.demo.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockServieImpl implements StockService {
  @Autowired
  private StockRepository stockRepository;
  @Autowired
  private RedisTemplate<String, String> redisTemplate;
  @Autowired
  private StockMapper stockMapper;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private StockPriceRepository stockPriceRepository;

  @Override
  public List<StockEntity> saveAll(List<StockEntity> entities) {
    return this.stockRepository.saveAll(entities);
  }

  @Override
  public List<StockEntity> findAll() {
    return this.stockRepository.findAll();
  }

  @Override
  public StockEntity findBySymbol(String Symbol) {
    return this.stockRepository.findBySymbol(Symbol).orElseThrow(
        () -> new BusinessException(ErrorCode.Entity_NOT_FOUND_EX));
  }

  @Override
  public List<Stock> findAllWithCache() throws JsonProcessingException {
    // read from Redis ...
    String json = this.redisTemplate.opsForValue().get("stock-list");
    if (json == null) {
      // read from DB ...
      List<Stock> stocks = this.stockRepository.findAll().stream() //
          .map(s -> this.stockMapper.map(s)) //
          .collect(Collectors.toList());
      // write to Redis ...
      String jsonToWrite = this.objectMapper.writeValueAsString(stocks);
      this.redisTemplate.opsForValue().set("stock-list", jsonToWrite,
          Duration.ofMinutes(10));
      return stocks;
    }
    StockEntity[] stockEntities =
        this.objectMapper.readValue(json, StockEntity[].class);
    return Arrays.asList(stockEntities).stream() //
        .map(e -> this.stockMapper.map(e)) //
        .collect(Collectors.toList());
  }

  @Override
  public String getMaxDay(String symbol) {
    String json = this.redisTemplate.opsForValue().get(symbol);
    if (json == null) {
      List<String> date = this.stockPriceRepository.findAll().stream()
          .map(d -> d.getMarketDateTime().toLocalDate().toString())
          .collect(Collectors.toList());
      String maxDate = date.stream() // 如果需要最大日期
          .max(String::compareTo) // 獲取最大日期
          .orElse("No data available");

      return "SYSDATE-" + symbol + ": " + maxDate;
    }
    return "SYSDATE-" + symbol + ":" + json;
  }

  @Override
  public APIDTO get5minData(String symbol) {
    String json = this.redisTemplate.opsForValue().get("5MIN-"+symbol);
    System.out.println("try get5minData^^ json!!!^^^^^:" + json);
    if (json == null) {
      APIDTO result = APIDTO.builder()//
          .regularMarketTime(LocalDate.now().atStartOfDay(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli())//
          .build();
      List<APIDTO.Data> data = this.stockPriceRepository.findAll().stream()//
          .filter(stock -> symbol.equals(stock.getSymbol()))//
          //.max((t1, t2) -> t1.getMarketDateTime().compareTo(t2.getMarketDateTime()))
          .map(stockEntity -> this.stockMapper.map(stockEntity))
          .collect(Collectors.toList());

          result.setData(data);
      redisTemplate.opsForValue().set("5MIN-"+symbol, result.toString());
      System.out.println("try get5minData^^^^^^^:" + result);
      return result;
    }
    try {
      return objectMapper.readValue(json, APIDTO.class);
  } catch (JsonProcessingException e) {
      throw new RuntimeException("Error parsing stock data", e);
  }
  }

  public static class PricePoint {
    private LocalDateTime timestamp;
    private double closePrice;

    public PricePoint(LocalDateTime timestamp, double closePrice) {
      this.timestamp = timestamp;
      this.closePrice = closePrice;
    }

    public LocalDateTime getTimestamp() {
      return this.timestamp;
    }

    public double getClosePrice() {
      return this.closePrice;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (!(obj instanceof PricePoint))
        return false;
      PricePoint point = (PricePoint) obj;
      return Objects.equals(this.timestamp, point.getTimestamp());
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.timestamp);
    }
  }
}

// List<String> symbols = stocks.stream() //
// .map(s -> s.getSymbol()) //
// .collect(Collectors.toList());
// // System.out.println("symbols=" + symbols);
// YahooQuoteDTO quoteDTO = this.yahooFinanceService.getQuote(symbols);
// quoteDTO.getBody().getResults().forEach(s -> {
// StockEntity stockEntity = this.stockService.findBySymbol(s.getSymbol());
// StockPriceEntity stockPriceEntity = this.stockPriceMapper.map(s);
// stockPriceEntity.setStock(stockEntity);
// stockPriceEntity.setSymbol(stockEntity.getSymbol());
// stockPriceEntity.setTranType("5MIN");
// this.stockPriceRepository.save(stockPriceEntity);
// System.out.println("stockPriceEntity**********" +stockPriceEntity);

// // Save into Redis
// String redisKey = stockEntity.getSymbol();
// LocalDate systemDate = stockPriceEntity.getMarketDateTime().toLocalDate();
// String formattedDate = systemDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
// redisTemplate.opsForValue().set(redisKey , formattedDate, 8, TimeUnit.HOURS);
// });   