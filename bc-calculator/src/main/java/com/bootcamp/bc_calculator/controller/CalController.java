package com.bootcamp.bc_calculator.controller;

import main.java.com.bootcamp.bc_calculator.service.CalculateService;

@RestController
public class CalController implements CalOperation {
    @Override
    public String<CalOperation> runAns(){
        return CalculateService.runAns();
    }
    
}
