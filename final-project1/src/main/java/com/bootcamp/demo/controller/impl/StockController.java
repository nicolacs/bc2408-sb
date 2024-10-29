package com.bootcamp.demo.controller.impl;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.controller.StockOperation;
import com.bootcamp.demo.model.StockDTO;
import com.bootcamp.demo.service.StockService;

@RestController
public class StockController implements StockOperation{

    @Autowired
    private StockService stockService;
    
    @Override
    public List<StockDTO> getPrice(List<String> symbols)throws IOException, InterruptedException{
        return stockService.getPrice(symbols);
    }
}
