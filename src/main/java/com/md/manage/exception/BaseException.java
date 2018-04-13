package com.md.manage.exception;

public class BaseException extends RuntimeException{


    protected Integer code=404;

    protected Integer errorCode=10001;

    public BaseException(String message, Integer code, Integer errorCode) {
        super(message);
        this.code = code;
        this.errorCode = errorCode;
    }

    public BaseException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
