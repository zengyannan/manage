package com.md.manage.json;

public class JsonResult {

    private static final String OK = "ok";
    private static final String ERROR = "error";

    private int code;

    private int errorCode;

    private Meta meta;

    private Object data;


    public JsonResult success(Object data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return this;
    }

    public JsonResult failure() {
        this.meta = new Meta(false, ERROR);
        return this;
    }
    public JsonResult failure(String message) {
        this.meta = new Meta(false, message);
        return this;
    }
    public JsonResult failure(String message,int code,int errorCode) {
        this.meta = new Meta(false, message,code,errorCode);
        this.code=code;
        this.errorCode=errorCode;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    public class Meta {

        private boolean success;
        private String message;
        private int code;
        private int errorCode;

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
        public Meta(boolean success, String message,int code,int errorCode) {
            this.success = success;
            this.message = message;
            this.code=code;
            this.errorCode=errorCode;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }

}