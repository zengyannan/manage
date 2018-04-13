package com.md.manage.exception;

public class RoleException extends BaseException {

    protected Integer code=401;

    protected Integer errorCode=30001;



    public RoleException(String message, Integer code, Integer errorCode) {
        super(message, code, errorCode);
    }

    public RoleException(String message) {
        super(message);
    }

}
