package com.qiroldev.monomarket.utils.exception;

/**
 * Ошибки в бизнес логике приложения
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Object... args) {
        super(String.format(message, args));
    }
}