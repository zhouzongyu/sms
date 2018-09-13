package com.gosuncn.ics.sms.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 服务启动后执行指定代码,可用于测试时模拟数据加载
 */
@Component
public class Startup implements CommandLineRunner {

    @Value("${server.port}")
    String port;
    @Value("${server.servlet.context-path}")
    String contextPath;
    @Value("${management.server.port}")
    String actuatorPort;
    @Value("${management.server.servlet.context-path}")
    String actuatorContextPath;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(String... args) throws Exception {

        logger.info("SMS-service startup success,please access it by http://localhost:"+port+contextPath+"/");
        logger.info("if you enable actuator,access it by http://localhost:"+actuatorPort+actuatorContextPath+"/actuator/health");
    }
}
