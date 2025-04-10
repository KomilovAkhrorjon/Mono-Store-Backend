package com.qiroldev.monomarket.security.config;

import com.qiroldev.monomarket.utils.exception.ApiError;
import com.qiroldev.monomarket.utils.exception.ApiException;
import com.qiroldev.monomarket.utils.exception.ApiValidationError;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(SQLException.class)
  protected ResponseEntity<Object> handleSQLException(
      SQLException ex,
      WebRequest request
  ) {
    log.info("SQLException", ex);
    ApiError apiError = generateWithInfoSysError("SQL Error", ex.getMessage(),
        HttpStatus.LOCKED, List.of(), List.of());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(ApiException.class)
  protected ResponseEntity<Object> apiExceptionHandler(
      ApiException ex,
      WebRequest request
  ) {
    log.info("ApiException", ex);
    ApiError apiError = generateWithInfoSysError(ex.getMessage(), ex.getDebugMessage(),
        ex.getStatus(), ex.getParams(), ex.getErrors());
    return buildResponseEntity(apiError);
  }

  public ApiError generateWithInfoSysError(String key, String message, HttpStatus status,
      List<String> params, List<ApiValidationError> errors) {

    var replacedParams = params.stream().map(
        param -> {
          if (param.isBlank() || param.isEmpty()) {
            return param.replace("", "_");
          }
          return param;
        }
    ).collect(Collectors.joining("_"));

    var apiError = new ApiError();
    apiError.setStatus(status);
    apiError.setMessage(key + ' ' + replacedParams);
    apiError.setInformationSystemsErrorCodeId(0L);
    apiError.setDebugMessage(message);
    apiError.setErrors(errors);
    return apiError;
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

}
