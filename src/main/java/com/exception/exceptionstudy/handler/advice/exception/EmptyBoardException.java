package com.exception.exceptionstudy.handler.advice.exception;

import com.exception.exceptionstudy.handler.advice.payload.ErrorCode;
import lombok.Getter;

@Getter
public class EmptyBoardException extends IllegalArgumentException {
    private final ErrorCode errorCode;

    public EmptyBoardException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
