package com.fengbin.crowd.exception;

/**
 * @类描述 登录失败抛出的异常
 * @作者 冯彬
 * @时间 2020-06-08-22:58
 */

public class LoginFailedException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }




}
