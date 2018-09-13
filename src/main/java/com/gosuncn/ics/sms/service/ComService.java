package com.gosuncn.ics.sms.service;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 串口服务
 */
@Service
public class ComService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    int bauds[] = {115200,57600,19200,9600};//串口波特率
   // int bauds[] = {9600,19200,57600,115200};//串口波特率

    /**
     * 检测所有com口
     *
     * @return
     */
    public List<CommPortIdentifier> findAllComs() {

        List<CommPortIdentifier> coms = new ArrayList<>();
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier
                .getPortIdentifiers();
        while (portList.hasMoreElements()) {
            CommPortIdentifier portId = portList
                    .nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                logger.info("找到串口：" + portId.getName());
                coms.add(portId);
            }
        }
        return coms;
    }

    /**
     * 检测串口是否可用
     *
     * @param serialPortName 串口名称，如“COM1”
     * @return
     */
    public boolean isSerialPortEnabled(String serialPortName) {

        CommPortIdentifier portId = null;
        try {
            portId = CommPortIdentifier.getPortIdentifier(serialPortName);
            SerialPort port = (SerialPort) portId.open("SMSLibCommTester", 1000);
            logger.info("SerialPort: " + port.getName() + " : BaudRate=" + port.getBaudRate());
            port.close();
            return true;
        } catch (NoSuchPortException e) {
            logger.info("找不到串口 " + serialPortName);

        } catch (PortInUseException e) {
            logger.info("串口 " + serialPortName + " 被占用");
        } catch (Exception e) {
            logger.info("产生异常：" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获得串口信息
     *
     * @param serialPortName
     * @return
     */
    @Deprecated
    public SerialPort getSerialPortinfo(String serialPortName) {

        CommPortIdentifier portId = null;
        try {
            portId = CommPortIdentifier.getPortIdentifier(serialPortName);
            SerialPort port = (SerialPort) portId.open("SMSLibCommTester", 1000);
            logger.info("SerialPort:" + port.getName() + " : BaudRate=" + port.getBaudRate());
            port.close();
            return port;
        } catch (NoSuchPortException e) {
            logger.info("找不到串口 " + serialPortName);

        } catch (PortInUseException e) {
            logger.info("串口 " + serialPortName + " 被占用");
        }
        return null;
    }


    /**
     * 获得串口可用的波特率
     *
     * @param serialPortName
     * @return -1表示失败
     */
    public int getBaudRate(String serialPortName) {

        CommPortIdentifier portId = null;
        try {
            portId = CommPortIdentifier.getPortIdentifier(serialPortName);
            for (int i = 0; i < bauds.length; i++) {
                logger.info("  Trying at " + bauds[i] + "...");
                if(validBaud(portId,bauds[i])){
                    return bauds[i];
                }
            }
        } catch (NoSuchPortException e) {
            logger.info("找不到串口 " + serialPortName);
        }
        return -1;
    }

    /**
     * 验证串口的波特率是否正确
     *
     * @param portId 串口对象
     * @param baud   待验证的波特率，一般为9600, 19200, 57600, 115200
     */
    private boolean validBaud(CommPortIdentifier portId, int baud) {
        SerialPort serialPort = null;
        InputStream inStream;
        OutputStream outStream;
        int c;
        String response;
        try {
            serialPort = (SerialPort) portId.open(
                    "SMSLibCommTester", 1000);
            serialPort
                    .setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
            serialPort.setSerialPortParams(baud,
                    SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            inStream = serialPort.getInputStream();
            outStream = serialPort.getOutputStream();
            serialPort.enableReceiveTimeout(1000);
            c = inStream.read();
            while (c != -1) {
                c = inStream.read();
            }
            outStream.write('A');
            outStream.write('T');
            outStream.write('\r');

            sleep(500);

            response = "";
            c = inStream.read();
            while (c != -1) {
                response += (char) c;
                c = inStream.read();
            }
            if (response.indexOf("OK") >= 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("产生异常：" + e.getMessage());
        } finally {
            if(serialPort!=null){
                serialPort.close();
            }

        }

        return false;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检测可用的串口
     */
    @Deprecated
    public String findComm() {

        Enumeration<CommPortIdentifier> portList = CommPortIdentifier
                .getPortIdentifiers();
        logger.info("短信设备端口连接测试...");
        //connect("COM13", 115200);
        while (portList.hasMoreElements()) {

            CommPortIdentifier portId = (CommPortIdentifier) portList
                    .nextElement();

            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                logger.info("找到串口：" + portId.getName());

                for (int i = 0; i < bauds.length; i++) {
                    logger.info("  Trying at " + bauds[i] + "...");
                    SerialPort serialPort = null;
                    try {
                        InputStream inStream;
                        OutputStream outStream;
                        int c;
                        String response;
                        serialPort = (SerialPort) portId.open(
                                "SMSLibCommTester", 1000);
                        serialPort
                                .setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
                        serialPort.setSerialPortParams(bauds[i],
                                SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);
                        inStream = serialPort.getInputStream();
                        outStream = serialPort.getOutputStream();
                        serialPort.enableReceiveTimeout(1000);
                        c = inStream.read();
                        while (c != -1)
                            c = inStream.read();
                        outStream.write('A');
                        outStream.write('T');
                        outStream.write('\r');
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                        response = "";
                        c = inStream.read();
                        while (c != -1) {
                            response += (char) c;
                            c = inStream.read();
                        }
                        if (response.indexOf("OK") >= 0) {
                            String portname = portId.getName();
                            int baud = bauds[i];
                            logger.info("找到设备：" + portname + baud);
                            break;
                        } else
                            logger.info("  没有发现设备!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.info("产生异常：" + e.getMessage());
                    } finally {
                        serialPort.close();
                    }
                }
            }
        }
        return "";
    }
}
