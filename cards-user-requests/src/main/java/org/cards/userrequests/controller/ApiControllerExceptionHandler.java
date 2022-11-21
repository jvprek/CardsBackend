package org.cards.userrequests.controller;

import lombok.extern.slf4j.Slf4j;
import org.cards.userrequests.service.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiControllerExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Default fall back
     */
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity fallBackHandleException(
            Exception ex, WebRequest request) {

        log.warn(request.toString());
        var errDto = buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", request);
        return handleExceptionInternal(ex, errDto,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity handleAppNotFoundException(
            NotFoundException ex,
            WebRequest request) {

        log.warn("NotFoundException Exception {}", ex.getMessage());

        var errDto =  buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
        return handleExceptionInternal(ex, errDto,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        log.warn(request.toString());
        var errDto = buildError(HttpStatus.BAD_REQUEST, ex, request);
        return handleExceptionInternal(ex, errDto,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private ApiErrorDto buildError(HttpStatus status, String message, WebRequest request) {
        return new ApiErrorDto(
                status.value(),
                message,
                LocalDateTime.now(),
                getMethod(request),
                getPath(request)
        );
    }

    private ApiErrorDto buildError(HttpStatus status, MethodArgumentNotValidException ex, WebRequest request) {
        var errorResponse = new ApiErrorDto(
                status.value(),
                "Validation Error",
                LocalDateTime.now(),
                getMethod(request),
                getPath(request)
        );
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(),
                    fieldError.getDefaultMessage(),
                    fieldError.getRejectedValue());
        }
        return errorResponse;
    }

    private String getPath(WebRequest request) {
        var url = ((ServletWebRequest) request).getRequest().getRequestURI();
        return url.substring(url.indexOf('/'));
    }

    private String getMethod(WebRequest request) {
        return ((ServletWebRequest) request).getHttpMethod().name();
    }


}
