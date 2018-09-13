package com.gosuncn.ics.sms.service;

import org.springframework.stereotype.Service;

/**
 * 测试用
 */
@Service
public class TestService {

    public String test(String content){
        return "hello:"+content;
    }
}
