package com.qiroldev.monomarket.utils.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;

public class BadRequestException extends ApiException {

    public BadRequestException(String message) {
        super(BAD_REQUEST, message);
    }

    public BadRequestException(String message, List<String> params) {
        super(BAD_REQUEST, message, params);
    }

    public BadRequestException(String objectName, String fieldName, List<String> params) {
        super(BAD_REQUEST, getMessage(objectName, fieldName), params);
    }

    public BadRequestException(String objectName, String fieldName) {
        super(BAD_REQUEST, getMessage(objectName, fieldName), new ApiValidationError(objectName, fieldName, "Is not valid"));
    }

    public BadRequestException(String objectName, String fieldName, List<String> params, String debugMessage) {
        super(BAD_REQUEST, getMessage(objectName, fieldName), params, debugMessage);
    }

    public BadRequestException(String objectName, String fieldName, String message, List<String> params) {
        super(BAD_REQUEST, getMessage(objectName, fieldName), params, new ApiValidationError(objectName, fieldName, message));
    }

    public BadRequestException(String objectName, String fieldName, String rejectedValue, String message) {
        super(BAD_REQUEST, getMessage(objectName, fieldName), new ApiValidationError(objectName, fieldName, rejectedValue, message));
    }

    public static String getMessage(String objectName, String fieldName) {
        return objectName.toLowerCase().trim() + "." + fieldName.toLowerCase().trim();
    }
}
