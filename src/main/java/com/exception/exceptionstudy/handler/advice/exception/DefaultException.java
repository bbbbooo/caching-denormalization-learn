package com.exception.exceptionstudy.handler.advice.exception;

import com.exception.exceptionstudy.handler.advice.payload.ErrorCode;
import lombok.Getter;

@Getter
public class DefaultException extends RuntimeException{
    private final ErrorCode errorCode;

    public DefaultException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
