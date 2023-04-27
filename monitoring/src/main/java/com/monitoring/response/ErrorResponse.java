package com.monitoring.response;

import com.monitoring.model.ExceptionCode;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private String code;
    private String message;

    public ErrorResponse(ExceptionCode exceptionCode){
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }

}
