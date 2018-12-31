package com.mad.projects.finance.domain.vo;

public class BaseError {
    String message;
    //type of exception thrown.
    String type;
    Integer code;

    public BaseError(String message, String type, Integer code) {
        this.message = message;
        this.type = type;
        this.code = code;
    }

    public BaseError() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
