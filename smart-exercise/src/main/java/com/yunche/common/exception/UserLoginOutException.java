package com.yunche.common.exception;

// 自定义用户退出异常
public class UserLoginOutException extends RuntimeException {
    public UserLoginOutException(String message) {
        super(message);
    }
}