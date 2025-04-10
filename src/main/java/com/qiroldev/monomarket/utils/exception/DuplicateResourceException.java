package com.qiroldev.monomarket.utils.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;
import org.springframework.http.HttpStatus;

public class DuplicateResourceException extends ApiException {

  public DuplicateResourceException(String objectName, String fieldName) {
    super(HttpStatus.BAD_REQUEST, getMessage(objectName, fieldName),
        new ApiValidationError(objectName, fieldName, "", "already exists!"));
  }

  public DuplicateResourceException(String message, List<String> params) {
    super(BAD_REQUEST, message, params);
  }

  public DuplicateResourceException(String objectName, String fieldName, List<String> params) {
    super(BAD_REQUEST, getMessage(objectName, fieldName), params);
  }

  public DuplicateResourceException(String message, List<ApiValidationFieldError> fieldErrors,
      List<String> params, String debugMessage) {
    super(HttpStatus.BAD_REQUEST, message, fieldErrors, params, debugMessage);
  }

  public static String getMessage(String objectName, String fieldName) {
    return objectName.toLowerCase().trim() + "." + fieldName.toLowerCase().trim();
  }
}
