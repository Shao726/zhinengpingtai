package com.dite.znpt.exception;

/**
 * @description: 服务异常
 */
public class ServiceException extends RuntimeException {
    private Integer code;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }
}
