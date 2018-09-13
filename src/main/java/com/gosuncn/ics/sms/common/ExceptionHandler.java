package com.gosuncn.ics.sms.common;

import com.gosuncn.ics.sms.utils.ApiResultUtil;
import com.gosuncn.ics.sms.bean.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
/**
 * 异常处理器，捕获所有异常，并按照统一格式返回
 * Created by hwj on 2017/5/21.
 */
@ControllerAdvice
public class ExceptionHandler {
    Logger logger=LoggerFactory.getLogger(this.getClass());

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult<String> handle(HttpServletRequest request, Exception e )  {
        if(e instanceof HttpRequestMethodNotSupportedException){
            return ApiResultUtil.error(-1,"请求方式(Get/Post)错误","HttpRequestMethodNotSupportedException");
        }else if(e instanceof MissingServletRequestParameterException){
            return ApiResultUtil.error(-1,"缺少参数","MissingServletRequestParameterException");
        }else if(e instanceof MethodArgumentTypeMismatchException){
            return ApiResultUtil.error(-1,"参数类型错误","MethodArgumentTypeMismatchException");
        }/*else if(e instanceof EmptyResultDataAccessException){
            ApiResultUtil.error(-1,"数据库不存在此记录","EmptyResultDataAccessException");
        }else if(e instanceof DataIntegrityViolationException){
            return ApiResultUtil.error(-1,"Insert或Update数据时违反了完整性，例如违反了惟一性限制,请检查参数内容是否合法 ","DataIntegrityViolationException");
        }*/else if(e instanceof BusinessException){
            return ApiResultUtil.error(((BusinessException)e).getCode(), e.getMessage());
        }

        e.printStackTrace();
        logger.error("系统异常:" + e.getClass());
        return ApiResultUtil.error(-1, "未知错误", e.getClass().toString());
    }
}
