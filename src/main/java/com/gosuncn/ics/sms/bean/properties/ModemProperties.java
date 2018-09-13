package com.gosuncn.ics.sms.bean.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "modem")
public class ModemProperties {
    private boolean enabled=false;
    private String comPort="COM1"; // COM1 #串口名称，一般为大写COM+数字，如COM1
    private int baudRate=115200;  //115200 #串口波特率,一般为115200
    private String manufacturer="wavecom"; // wavecom #开发商，一般为huawei和wavecom,可通过工具检测出来
    private String model="";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getComPort() {
        return comPort;
    }

    public void setComPort(String comPort) {
        this.comPort = comPort;
    }

    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
