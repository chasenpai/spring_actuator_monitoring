package com.monitoring.model;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    PRODUCT_NOT_FOUND("P001", "존재하지 않는 제품입니다."),
    INTERNAL_SERVER_ERROR("P002", "일시적으로 제품 확인이 불가능합니다.");

    private String code;
    private String message;

    ExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
