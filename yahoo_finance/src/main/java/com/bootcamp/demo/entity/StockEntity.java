package com.bootcamp.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tstocks")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "stock_id")
  private Long id;
  private String symbol;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockPriceEntity> stockPrices = new ArrayList<>();
  
  // @Builder.Default
  // @OneToMany(mappedBy = "stock",
  //     cascade = {CascadeType.PERSIST, CascadeType.MERGE},
  //     fetch = FetchType.LAZY)
  // private List<StockQuoteYahooEntity> stockQuotes = new ArrayList<>();
}