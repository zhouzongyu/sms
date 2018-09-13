package com.gosuncn.ics.sms.bean;
/**
 * 接口统一返回结果
 * Created by hwj on 2017/5/21.
 */
public class ApiResult<T> {
    private int code=-1;
    private String message="";
    private T data;

    public ApiResult(int code,String message,T data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
