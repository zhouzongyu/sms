package com.gosuncn.ics.sms.utils;

import com.gosuncn.ics.sms.bean.ApiResult;
import com.gosuncn.ics.sms.common.ResultCode;


public class ApiResultUtil {
    public static <T> ApiResult<T> success(String message, T data) {
        ApiResult<T> result = new ApiResult(ResultCode.SUCCESS.code, message, data);
        return result;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult(ResultCode.SUCCESS.code, ResultCode.SUCCESS.message, data);
        return result;
    }
    public static <T> ApiResult<T> success(String message) {
        ApiResult<T> result = new ApiResult(ResultCode.SUCCESS.code, message, "");
        return result;
    }
    public static <T> ApiResult<T> success() {
        ApiResult<T> result = new ApiResult(ResultCode.SUCCESS.code, ResultCode.SUCCESS.message, "");
        return result;
    }

    public static <T> ApiResult<T> failed() {
        ApiResult<T> result = new ApiResult(ResultCode.FAILED.code, ResultCode.FAILED.message, "");
        return result;
    }

    public static <T> ApiResult<T> failed(String message) {
        ApiResult<T> result = new ApiResult(ResultCode.FAILED.code, message, "");
        return result;
    }

    public static <T> ApiResult<T> failed(String message, T t) {
        ApiResult<T> result = new ApiResult(ResultCode.FAILED.code, message, t);
        return result;
    }


    public static <T> ApiResult<T> error(int errorCode, String message) {
        ApiResult<T> result = new ApiResult(errorCode, message, "");
        return result;
    }

    public static <T> ApiResult<T> error(int errorCode, String message, T t) {
        ApiResult<T> result = new ApiResult(errorCode, message, t);
        return result;
    }


}
