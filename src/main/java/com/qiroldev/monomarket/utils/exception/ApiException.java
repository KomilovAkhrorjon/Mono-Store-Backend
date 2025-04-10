package com.qiroldev.monomarket.utils.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@JsonIgnoreProperties
@JsonPropertyOrder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
public class ApiException extends RuntimeException {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<String> params = new ArrayList<>();
    private List<ApiValidationError> errors = new ArrayList<>();


    private ApiException() {
        timestamp = LocalDateTime.now();
    }

    public ApiException(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public ApiException(ApiError error) {
        this.status = error.getStatus();
        this.timestamp = error.getTimestamp();
        this.message = error.getMessage();
        this.debugMessage = error.getDebugMessage();
        this.errors = error.getErrors();
    }

    public ApiException(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiException(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiException(HttpStatus status, String message, ApiValidationError error) {
        this();
        this.status = status;
        this.message = message;
        this.addSubError(error);
    }

    public ApiException(HttpStatus status, String message, List<String> params, ApiValidationError error) {
        this();
        this.status = status;
        this.message = message;
        this.params = params;
        this.addSubError(error);
    }

    public ApiException(HttpStatus status, String message, List<String> params) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.params = params;
    }

    public ApiException(HttpStatus status, String message, List<String> params, String debugMessage) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.params = params;
        this.debugMessage = debugMessage;
    }

    public ApiException(HttpStatus status, String message, List<ApiValidationFieldError> fieldErrors, List<String> params, String debugMessage) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.params = params;
        fieldErrors.forEach(fieldError -> this.errors.add(new ApiValidationError("", fieldError.field(), fieldError.value().toString())));
        this.debugMessage = debugMessage;
    }


    private void addSubError(ApiValidationError subError) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(subError);
    }

    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationError(object, field, rejectedValue, message));
    }

    public void addValidationError(String object, String message) {
        addSubError(new ApiValidationError(object, message));
    }

    public void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    public void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    /**
     * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
     *
     * @param cv the ConstraintViolation
     */
    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }

    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }
}
