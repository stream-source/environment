package com.itcode.environment.result;


public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result() {

    }

    public int getCode() {
        return code;
    }

    public Result setCode(ResultCodeEnum resultCode) {
        this.code = resultCode.getCode();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString(){
        return "code:" + code + " | message:" + message;
    }
}
