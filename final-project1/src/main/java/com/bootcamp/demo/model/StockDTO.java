package com.bootcamp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
// private Double regularMarketChange; // 
// private Long regularMarketTime; //
// private Double regularMarketDayHigh; //
// private String regularMarketDayRange;
// private Double regularMarketDayLow; //
// private Long regularMarketVolume; //
// private Double regularMarketPreviousClose; //

private String regularMarketUnix;
private Double regularMarketPrice;
private Double regularMarketChangePercent;
private Double bid;
private Integer bidSize;
private Double ask;
private Integer askSize;
 

// private Double bid; // 
// private Double ask; //
private String symbol;
        
}
