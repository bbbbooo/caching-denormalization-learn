package com.exception.exceptionstudy.handler.advice.payload;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    INVALID_PARAMETERS(400, "C001", "잘못된 파라미터를 전달하였습니다."),
    INVALID_INPUT_VALUE(400, "C002", "유효하지 않는 값을 입력하셨습니다.");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ErrorResponse toErrorResponse() {
        return ErrorResponse.builder()
                .status(this.status)
                .code(this.code)
                .message(this.message)
                .build();
    }

    public HttpStatusCode toHttpStatus() {
        return HttpStatus.valueOf(this.status);
    }
}
