package com.md.manage.exception;

public class OrganException extends BaseException {


    protected Integer code=401;

    protected Integer errorCode=40001;



    public OrganException(String message, Integer code, Integer errorCode) {
        super(message, code, errorCode);
    }

    public OrganException(String message) {
        super(message);
    }

}
