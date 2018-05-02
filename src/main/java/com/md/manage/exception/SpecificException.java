package com.md.manage.exception;

public class SpecificException extends BaseException{

    protected Integer code=401;

    protected Integer errorCode=60001;



    public SpecificException(String message, Integer code, Integer errorCode) {
        super(message, code, errorCode);
    }

    public SpecificException(String message) {
        super(message);
    }

}
