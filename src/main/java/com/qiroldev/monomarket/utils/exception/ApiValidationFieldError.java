package com.qiroldev.monomarket.utils.exception;

public record ApiValidationFieldError(String field, Object value) {
}
