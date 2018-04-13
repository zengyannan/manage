package com.md.manage.exception;

public class DoctorException extends BaseException {

    protected Integer code=401;

    protected Integer errorCode=40001;



    public DoctorException(String message, Integer code, Integer errorCode) {
        super(message, code, errorCode);
    }

    public DoctorException(String message) {
        super(message);
    }
}
