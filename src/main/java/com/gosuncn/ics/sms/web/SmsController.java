package com.gosuncn.ics.sms.web;

import com.gosuncn.ics.sms.bean.ApiResult;
import com.gosuncn.ics.sms.service.AliyunService;
import com.gosuncn.ics.sms.service.ModemService;
import com.gosuncn.ics.sms.utils.ApiResultUtil;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @apiDefine smsGroup 短信接口
 */
@RestController
@RequestMapping(value = "/msg")
public class SmsController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ModemService modemService;

    @Autowired
    AliyunService aliyunService;

    /**
     * @api {get} /sms/msg/sendTemplate 模板发送短信
     * @apiName 模板发送短信
     * @apiGroup smsGroup
     * @apiVersion 1.0.0
     * @apiDescription 当使用aliyun发送短信时可以使用此接口，假如模板为“hello:${name}”,那么模板参数应为json：{"name":"Tom"}
     * @apiParam {String}           phone       手机号码
     * @apiParam {String}           [signName]       短信签名，从短信服务商获取，如果不传，则采用默认值
     * @apiParam {String}           templateCode       短信模板，从短信服务商获取
     * @apiParam {String}           templateParam      模板参数，根据实际的短信服务商说明设置
     * @apiUse CODE_200
     */
    @GetMapping("/sendTemplate")
    public ApiResult<Boolean> send(
            @RequestParam String phone
            , @RequestParam(required = false) String signName
            , @RequestParam String templateCode
            , @RequestParam String templateParam
    ) {
        if(!aliyunService.getAliyunEnabled()){
            return ApiResultUtil.failed("阿里云短信服务未开启，请检查配置文件");
        }
        if (TextUtils.isBlank(signName)) {
            signName = aliyunService.getDefaultSignName();
        }
        boolean bool = aliyunService.send(phone, signName, templateCode, templateParam);
        if (bool) {
            return ApiResultUtil.success("发送成功");
        }
        return ApiResultUtil.failed("发送失败");
    }

    /**
     * @api {get} /sms/msg/send 短信猫发送短信
     * @apiName 短信猫发送短信
     * @apiGroup smsGroup
     * @apiVersion 1.0.0
     * @apiDescription 当采用短信猫发送时可以使用此接口
     * @apiParam {String}           phone       手机号码
     * @apiParam {String}           message     发送内容
     * @apiUse CODE_200
     */
    @GetMapping("/send")
    public ApiResult<Boolean> send(
            @RequestParam String phone
            , @RequestParam String message
    ) {
        if(!modemService.getModemEnabled()){
            return ApiResultUtil.failed("短信猫服务未开启，请检查配置文件");
        }
        boolean bool = modemService.sendSMS(phone, message);
        if (bool) {
            return ApiResultUtil.success("发送成功");
        }
        return ApiResultUtil.failed("发送失败");
    }


}
