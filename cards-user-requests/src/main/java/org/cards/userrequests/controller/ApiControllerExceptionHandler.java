package org.cards.userrequests.controller;

import lombok.extern.slf4j.Slf4j;
import org.cards.userrequests.service.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.function.Function;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public void handleNotFoundException(NotFoundException ex, HttpServletResponse response) throws IOException {

        log.warn("NotFoundException Exception {}", ex.getMessage());

        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void customHandleUnexpecteException(Exception ex, HttpServletResponse response) throws IOException {
        log.warn("Unexpected Exception {}", ex.getMessage());
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void customHandleConstraintViolationException(ConstraintViolationException ex, HttpServletResponse response) throws IOException {

        log.warn("Constraint Exception {}", ex.getMessage());

        var errorMessage = formatConstraintViolationDetails(ex);
        response.sendError(HttpStatus.BAD_REQUEST.value(), errorMessage);
    }

    private String formatConstraintViolationDetails(ConstraintViolationException ex){
        Function<ConstraintViolation<?>, String> formatConstraintViolation = (x) ->
        {
            var sb = new StringBuilder();
            sb.append("{Path:" + x.getPropertyPath());
            if (x.getInvalidValue() != null) {
                sb.append(", Invalid Value:" + x.getInvalidValue());
            }
            sb.append(", Error:" + x.getMessage()+"}");
            return sb.toString();
        };

        var optErrorMessage = ex.getConstraintViolations().stream().map(formatConstraintViolation).reduce((x, y) -> x + ", " + y);
        var errorDetails = optErrorMessage.orElse("");
        return "Constraint Violations: [" + errorDetails + "]";
    }

}
