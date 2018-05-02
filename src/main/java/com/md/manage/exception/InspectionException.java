package com.md.manage.exception;

public class InspectionException extends BaseException {

    protected Integer code=401;

    protected Integer errorCode=70001;



    public InspectionException(String message, Integer code, Integer errorCode) {
        super(message, code, errorCode);
    }

    public InspectionException(String message) {
        super(message);
    }

}
