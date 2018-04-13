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
        this.meta = new Meta(false, message);
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

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }

}