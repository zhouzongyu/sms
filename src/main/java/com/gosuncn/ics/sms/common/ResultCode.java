package com.gosuncn.ics.sms.common;
/**
 * 返回码枚举类型
 * Created by hwj on 2017/5/21.
 */
public enum ResultCode {

    UNKNOW(-99, "未知错误")
    ,SUCCESS(1, "操作成功")
    ,FAILED(-1, "操作失败")
    ,ALIYUN_CLIENT_FAILED(10001, "阿里云短信客户端异常，请检查阿里云配置是否正确，网络是否畅通")
    ;


    public int code;
    public String message;
     ResultCode(int code,String message){
        this.code=code;
        this.message=message;

    }

}
