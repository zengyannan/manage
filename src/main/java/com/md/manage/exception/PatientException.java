package com.md.manage.exception;

public class PatientException extends BaseException{


    protected Integer code=401;

    protected Integer errorCode=80001;



    public PatientException(String message, Integer code, Integer errorCode) {
        super(message, code, errorCode);
    }

    public PatientException(String message) {
        super(message);
    }

}
