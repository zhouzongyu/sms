package com.gosuncn.ics.sms.service;

import com.gosuncn.ics.sms.bean.properties.ModemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.Message;
import org.smslib.OutboundMessage;
import org.smslib.modem.SerialModemGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 短信猫服务
 */
@Service
public class ModemService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ModemProperties modemProperties;
    @Autowired(required = false)
    org.smslib.Service modemService;

    @Bean
    public org.smslib.Service service(SerialModemGateway serialModemGateway) {
        if (!modemProperties.isEnabled()) {
            logger.info("未配置使用短信猫，短信猫服务不启动");
            return null;
        }
        // -----------------创建发送短信的服务（它是单例的）----------------
        org.smslib.Service service = org.smslib.Service.getInstance();
        org.smslib.Service.getInstance().getSettings().SERIAL_POLLING = true;
        try {
            service.addGateway(serialModemGateway);//将设备加到服务中
            service.startService();//启动服务
            logger.info("短信猫服务启动成功");
            /*// ------------------------- 关闭服务 -------------------------  
            modemService.stopService();
            modemService.removeGateway(gateway);*/
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("短信猫服务启动失败，请检查短信猫设置是否正确");
        }
        return service;
    }

    @Bean
    public SerialModemGateway serialModemGateway() {
        SerialModemGateway gateway = new SerialModemGateway(UUID.randomUUID().toString(), modemProperties.getComPort(), modemProperties.getBaudRate(), modemProperties.getManufacturer(), modemProperties.getModel());
        gateway.setInbound(true); // 设置true，表示该网关可以接收短信
        gateway.setOutbound(true); // 设置true，表示该网关可以发送短信
        return gateway;
    }

    /**
     * 发送短信
     * @param phone 手机号
     * @param content 发送内容
     * @return
     */
    public boolean sendSMS(String phone, String content) {
        try {
            // ------------------------- 发送短信 -------------------------  
            OutboundMessage msg = new OutboundMessage(phone, content);
            msg.setEncoding(Message.MessageEncodings.ENCUCS2);
            return modemService.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean getModemEnabled(){
        return  modemProperties.isEnabled();
    }
}
