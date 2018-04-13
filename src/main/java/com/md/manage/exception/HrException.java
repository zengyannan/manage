package com.md.manage.exception;

public class HrException extends BaseException {

    protected Integer code=401;

    protected Integer errorCode=10001;



    public HrException(String message, Integer code, Integer errorCode) {
        super(message, code, errorCode);
    }

    public HrException(String message) {
        super(message);
    }
}
