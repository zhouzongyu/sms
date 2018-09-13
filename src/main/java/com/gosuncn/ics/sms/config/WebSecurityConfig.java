package com.gosuncn.ics.sms.config;

import com.gosuncn.ics.sms.interceptor.TestInterceptor;
import com.gosuncn.ics.sms.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {


    @Override
    public void  addInterceptors(InterceptorRegistry registry) {
        // super.addInterceptors(registry)
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        //以下拦截器只是示例，用户请根据实际情况添加和修改
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
        //registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
