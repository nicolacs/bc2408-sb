package com.bootcamp.demo.service;

import java.io.IOException;
import java.util.List;
import com.bootcamp.demo.model.StockDTO;

public interface StockService {
    // KeyDTO getKey();
    List<StockDTO> getPrice(List<String> symbols) throws IOException, InterruptedException;
    
}
