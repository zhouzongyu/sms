package com.gosuncn.ics.sms.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.gosuncn.ics.sms.bean.properties.AliyunProperties;
import com.gosuncn.ics.sms.common.BusinessException;
import com.gosuncn.ics.sms.utils.ApiResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import static com.gosuncn.ics.sms.common.ResultCode.ALIYUN_CLIENT_FAILED;

@Service
public class AliyunService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    AliyunProperties aliyunProperties;
    @Autowired
    SendSmsRequest sendSmsRequest;
    @Autowired
    IAcsClient acsClient;

    @Bean
    protected IClientProfile clientProfile() {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", aliyunProperties.getConnectTimeout());
        System.setProperty("sun.net.client.defaultReadTimeout", aliyunProperties.getReadTimeout());
//替换成你的AK
        final String accessKeyId = aliyunProperties.getAccessKeyId();//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = aliyunProperties.getAccessKeySecret();//你的accessKeySecret，参考本文档步骤2
//初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        return profile;
    }

    @Bean
    protected IAcsClient acsClient(IClientProfile profile) {
        IAcsClient acsClient = new DefaultAcsClient(profile);
        return acsClient;
    }

    @Bean
    protected SendSmsRequest sendSmsRequest(IClientProfile profile) throws ClientException {
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //初始化ascClient,暂时不支持多region（请勿修改）
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);

        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        return request;
    }

    /**
     * 发送短信
     *
     * @param phone 手机号
     * @param signName 短信签名
     * @param templateCode 短信模板
     * @param templateParam 模板参数
     * @return
     */
    public boolean send(String phone, String signName, String templateCode, String templateParam) {

        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
        sendSmsRequest.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        sendSmsRequest.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        sendSmsRequest.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        sendSmsRequest.setTemplateParam(templateParam);
        //sendSmsRequest.setTemplateParam("{\"customer\":\"Tom\", \"code\":\"123\"}");

        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(sendSmsRequest);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new BusinessException(ALIYUN_CLIENT_FAILED, ALIYUN_CLIENT_FAILED.message);
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
            logger.info("aliyun send success");
            return true;
        }
        return false;
    }

    public String getDefaultSignName(){
       return  aliyunProperties.getSignName();
    }

    public boolean getAliyunEnabled(){
        return  aliyunProperties.isEnabled();
    }

}
