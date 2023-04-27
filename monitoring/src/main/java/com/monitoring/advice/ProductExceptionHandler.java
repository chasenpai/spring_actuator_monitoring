package com.monitoring.advice;

import com.monitoring.exception.ProductNotFoundException;
import com.monitoring.model.ExceptionCode;
import com.monitoring.response.ErrorResource;
import com.monitoring.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ProductExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResource handler(ProductNotFoundException exception){
        return new ErrorResource(new ErrorResponse(exception.getExceptionCode()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResource handler(Exception exception){
        return new ErrorResource(new ErrorResponse(ExceptionCode.INTERNAL_SERVER_ERROR));
    }

}
