package com.visionary.crofting.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class BusinessException extends Exception{
    List<String> errors;
    public BusinessException(String message, List<String> errors){
        super(message);
        this.errors=errors;
    }
}
