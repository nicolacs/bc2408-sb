package com.bootcamp.demo.infra;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.bootcamp.demo.entity.StockPriceEntity;

public class LineProcessor {
  private List<String> transactions;

  public LineProcessor(List<String> transactions) {
    this.transactions = transactions;
  }

  @Autowired
  private StockPriceEntity stockPriceEntity;

//   List<String> transaction = new ArrayList<>();
//    transaction = this.stockPriceEntity.findAll().stream()
        


}
