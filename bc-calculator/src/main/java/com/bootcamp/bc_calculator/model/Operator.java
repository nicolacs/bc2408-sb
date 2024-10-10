package com.bootcamp.bc_calculator.model;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public enum Operator {
    ADD("add"),
    SUBTRACT("sub"),
    MULTIPLY("mul"),
    DIVIDE("div"),
    ;

    private String operator;

    private Operator(String operator){
        this.operator= operator;
    }

    public String runAns(String x, String y, Operator operation){
        switch (operation) {
            case add -> {BigDecimal.valueOf(String.valueOf(x)).add(BigDecimal.valueOf(String.valueof(y))).toString();}
            case sub -> {BigDecimal.valueOf(String.valueOf(x)).substact(BigDecimal.valueOf(String.valueof(y))).toString();}
            case mul -> {BigDecimal.valueOf(String.valueOf(x)).multiply(BigDecimal.valueOf(String.valueof(y))).toString();}
            case div -> {if (BigDecimal.valueOf(String.valueOf(y)).equals(BigDecimal.ZERO)) {
                return  "y cannot be 0, please try again.";
            }
                return BigDecimal.valueOf(String.valueOf(x)).divide(BigDecimal.valueOf(String.valueof(y))).toString();
        } 
        default -> throw new IllegalAccessException("Invalid operation input.");
    } 

    public String calculate(BigDecimal x, BigDecimal y){
        return switch(this){
            case ADD -> x.add(y).setScale(5).toString();
            case SUBTRACT -> x.subtract(y).setScale(5).toString();
            case MULTIPLY -> x.multiply(y).setScale(5).toString();
            case DIVIDE ->{
                if (y.equals(BigDecimal.ZERO)){
                    throw new CannotDivideZeroException
                }
            }
        }
    }
}
}