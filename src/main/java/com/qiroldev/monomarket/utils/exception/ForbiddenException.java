package com.qiroldev.monomarket.utils.exception;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class ForbiddenException extends ApiException {

  public ForbiddenException(String objectName, String fieldName) {
    super(FORBIDDEN, getMessage(objectName, fieldName),
        new ApiValidationError(objectName, fieldName, "has not access"));
  }



  public static String getMessage(String objectName, String fieldName) {
    return objectName.toLowerCase().trim() + "." + fieldName.toLowerCase().trim();
  }
}
