package com.bootcamp.demo.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.management.RuntimeErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.entity.StockEntity;
import com.bootcamp.demo.entity.StockPriceEntity;
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
    String redisKey = "5MIN-" + symbol;
    String json = this.redisTemplate.opsForValue().get(redisKey);
    System.out.println("try get5minData^^ json!!!^^^^^:" + json);


    if (json == null){
      APIDTO result = APIDTO.builder()
      .regularMarketTime(LocalDate.now()
          .atStartOfDay(ZoneId.of("Asia/Shanghai"))
          .toInstant()
          .toEpochMilli())
          .build();

      Map<LocalDateTime, APIDTO.Data> groupedData = this.stockPriceRepository.findAll().stream()
          .filter(stock -> symbol.equals(stock.getSymbol()))
          .collect(Collectors.groupingBy(
            stock -> roundToNextInterval(stock.getMarketDateTime()),
            Collectors.collectingAndThen(
              Collectors.maxBy(Comparator.comparing(StockPriceEntity::getMarketDateTime)), 
              optionalStock -> optionalStock.map(stock -> {
                APIDTO.Data data = this.stockMapper.map(stock);

                data.setMarketTime(roundToNextInterval(stock.getMarketDateTime())
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                return data;
              }) .orElse(null))
              ));
      List<APIDTO.Data> data = groupedData.values().stream()
          .filter(Objects::nonNull)
         // .sorted(Comparator.comparing(APIDTO.Data::getMarketTime))
          .collect(Collectors.toList());
      result.setData(data);

      Set<PricePoint> pricePoint =data.stream()
          .sorted(Comparator.comparing(APIDTO.Data::getMarketTime))
          .map(t -> new PricePoint(t.getMarketTime(), t.getRegularMarketPrice()))
          .collect(Collectors.toCollection(LinkedHashSet::new));
      System.out.println("toSet~~~~~~~~~~~~~~~~~StockServiceimpl~~:" + pricePoint);

      try{
          String jsonResult = objectMapper.writeValueAsString(result);
          redisTemplate.opsForValue().set(redisKey, jsonResult);
          redisTemplate.expire(redisKey, 12, TimeUnit.HOURS);
          System.out.println("try get5minData^^^jsonResult^^^^:" + jsonResult);
      }catch (JsonProcessingException e){
          throw new RuntimeException("Error serializing stock data", e);
      }
      return result;
    }
    try{
      return objectMapper.readValue(json, APIDTO.class);
    } catch (JsonProcessingException e) {
        throw new RuntimeException("Error parsing stock data", e);
    }

    
  }


  private LocalDateTime roundToNextInterval(LocalDateTime dateTime) {
    int minutes = dateTime.getMinute();
    int roundedMinutes = Math.round((minutes / 5)+1 ) * 5;
    
    // 處理59分鐘的情況，可能需要進位到下一個小時
    if (roundedMinutes == 60) {
        return dateTime.plusHours(1).withMinute(0);
    }
    return dateTime
        .withMinute(roundedMinutes)
        .withSecond(0)
        .withNano(0);
  }

  public static class PricePoint {
    private String timestamp;
    private double closePrice;

    public PricePoint(String timestamp, double closePrice) {
      this.timestamp = timestamp;
      this.closePrice = closePrice;
    }

    public String getTimestamp() {
      return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
      this.timestamp = timestamp;
    }

    public double getClosePrice() {
      return this.closePrice;
    }

    @Override
    public String toString() {
      return "Transaction[" //
          + "timestamp=" + this.timestamp //
          + "closePrice" + this.closePrice //
          + "]";
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


  //   if (json == null) {
  //     APIDTO result = APIDTO.builder()//
  //         .regularMarketTime(LocalDate.now().atStartOfDay(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli())//
  //         .build();

  //     List<APIDTO.Data> data = this.stockPriceRepository.findAll().stream()//
  //         .filter(stock -> symbol.equals(stock.getSymbol()))//
  //         .map(stockEntity -> this.stockMapper.map(stockEntity))
  //         .collect(Collectors.toList());

  //         result.setData(data);
  //     redisTemplate.opsForValue().set("5MIN-"+symbol, result.toString());
  //     System.out.println("try get5minData^^^^^^^:" + result);
  //     return result;
  //   }
  //   try {
  //     return objectMapper.readValue(json, APIDTO.class);
  // } catch (JsonProcessingException e) {
  //     throw new RuntimeException("Error parsing stock data", e);
  // }
  // }