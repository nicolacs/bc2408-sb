package com.bootcamp.demo.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.model.StockDTO;


public interface StockOperation {
    @GetMapping("/stock/price")
    List<StockDTO> getPrice(@RequestParam List<String> symbols)throws IOException, InterruptedException;
    
}