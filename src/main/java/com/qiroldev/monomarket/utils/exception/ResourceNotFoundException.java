package com.qiroldev.monomarket.utils.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String message, List<String> params) {
        super(NOT_FOUND, message.toLowerCase().trim(), params);
    }

    public ResourceNotFoundException(String objectName, String fieldName) {
        super(NOT_FOUND, getMessage(objectName, fieldName), new ApiValidationError(objectName, fieldName, "Not found"));
    }

    public ResourceNotFoundException(String objectName, String fieldName, List<String> params) {
        super(NOT_FOUND, getMessage(objectName, fieldName), params, new ApiValidationError(objectName, fieldName, "Not found"));
    }

    public static String getMessage(String objectName, String fieldName) {
        return objectName.toLowerCase().trim() + "." + fieldName.toLowerCase().trim();
    }
}
