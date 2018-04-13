package com.md.manage.exception;

public class MenuException extends BaseException {

    protected Integer code=401;

    protected Integer errorCode=50001;



    public MenuException(String message, Integer code, Integer errorCode) {
        super(message, code, errorCode);
    }

    public MenuException(String message) {
        super(message);
    }
}
