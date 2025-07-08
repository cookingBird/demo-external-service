package com.example.demoexternalservice.commons.error;

import com.example.demoexternalservice.commons.error.api.ErrorType;

public class BizException extends RuntimeException {
    private final ErrorType errorType;

    public BizException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public int getCode() {
        return errorType.getCode();
    }
}
