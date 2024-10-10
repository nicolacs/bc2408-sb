package com.bootcamp.bc_calculator.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class CannotDivideZeroException extends Exception {
    private String message;
    
}
