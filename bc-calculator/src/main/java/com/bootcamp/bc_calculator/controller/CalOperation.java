package com.bootcamp.bc_calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.bc_calculator.model.Operator;

public interface CalOperation {
    @GetMapping("/operation/{x}/{y}{operation}")
    String<Operator> runAns(@RequestParam String x, @RequestParam String y,
            @PathVariable Operator operation);
}
