package com.fengbin.crowd.exception;

/**
 * @类描述 访问被拒绝的异常类
 * @作者 冯彬
 * @时间 2020-06-10-23:00
 */

public class AccessForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AccessForbiddenException() {
        super();
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    protected AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
