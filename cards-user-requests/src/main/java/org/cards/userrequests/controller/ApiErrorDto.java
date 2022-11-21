package org.cards.userrequests.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@RequiredArgsConstructor
public class ApiErrorDto {
    private final int status;
    private final String message;
    private final LocalDateTime time;
    private final String method;
    private final String path;
    private List<ValidationError> errors;

    @Data
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class ValidationError {
        private final String field;
        private final String message;
        private final String rejectedValue;
    }

    public void addValidationError(String field, String message, Object rejectedValue) {
        if (Objects.isNull(errors)) {
            errors = new ArrayList<>();
        }
        errors.add(new ValidationError(field, message, rejectedValue == null ? null : rejectedValue.toString()));
    }
}
