package com.monitoring.exception;

import com.monitoring.model.ExceptionCode;
import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public ProductNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

}
