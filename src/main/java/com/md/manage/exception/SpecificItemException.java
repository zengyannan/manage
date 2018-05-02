package com.md.manage.exception;

public class SpecificItemException extends BaseException {


    protected Integer code=401;

    protected Integer errorCode=90001;



    public SpecificItemException(String message, Integer code, Integer errorCode) {
        super(message, code, errorCode);
    }

    public SpecificItemException(String message) {
        super(message);
    }

}
