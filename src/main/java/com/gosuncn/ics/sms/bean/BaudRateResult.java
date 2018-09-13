package com.gosuncn.ics.sms.bean;

import java.io.Serializable;

public class BaudRateResult implements Serializable{
    private int baudRate;

    public BaudRateResult() {
    }

    public BaudRateResult(int baudRate) {

        this.baudRate = baudRate;
    }

    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }
}
