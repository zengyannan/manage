package com.md.manage.exception;

public class LaboratorySheetException extends BaseException{



    protected Integer code=401;

    protected Integer errorCode=1000001;



    public LaboratorySheetException(String message, Integer code, Integer errorCode) {
        super(message, code, errorCode);
    }

    public LaboratorySheetException(String message) {
        super(message);
    }


}
