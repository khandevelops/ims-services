package com.usdtl.ims.common.exceptions.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> handleRequestException(RequestException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }
}
