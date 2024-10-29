package com.bootcamp.demo.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TStock_Quote_Yahoo")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class StockEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;
     private String regularMarketUnix;
     private Double regularMarketPrice;
     private Double regularMarketChangePercent;
     private Double bid;
     private Integer bidSize;
     private Double ask;
     private Integer askSize;

    public StockEntity(String symbol){
      this.symbol = symbol;
    }
       
}
