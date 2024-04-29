package com.example.account.exception;


import com.example.account.type.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import com.example.account.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.account.type.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.example.account.type.ErrorCode.INVALID_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountException.class)
    public ErrorResponse handleAccountException(AccountException e) {
        log.error("{} is occured", e.getMessage());

        return new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException is occured", e.getMessage());

        return new ErrorResponse(INVALID_REQUEST,INVALID_REQUEST.getDescription());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        log.error("Exception occured", e);

        return new ErrorResponse(INVALID_REQUEST, INTERNAL_SERVER_ERROR.getDescription());
    }
}
