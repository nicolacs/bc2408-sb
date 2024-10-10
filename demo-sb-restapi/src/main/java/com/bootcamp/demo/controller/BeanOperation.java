package com.bootcamp.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

public interface BeanOperation {

    @GetMapping("/beans")
    List<String> getBeans();

   @GetMapping("/beans/x")
    int getX();
    
}
