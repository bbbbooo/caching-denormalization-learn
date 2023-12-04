package com.exception.exceptionstudy.handler;

import com.exception.exceptionstudy.handler.advice.exception.DefaultException;
import com.exception.exceptionstudy.handler.advice.payload.ErrorCode;
import com.exception.exceptionstudy.handler.advice.payload.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<ErrorResponse> handleDefaultException(DefaultException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.from(errorCode);

        return new ResponseEntity<>(errorResponse, errorCode.toHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ErrorResponse errorResponse = ErrorResponse.from(errorCode);

        return new ResponseEntity<>(errorResponse, errorCode.toHttpStatus());
    }
}
