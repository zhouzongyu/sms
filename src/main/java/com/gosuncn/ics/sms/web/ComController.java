package com.gosuncn.ics.sms.web;

import com.gosuncn.ics.sms.bean.ApiResult;
import com.gosuncn.ics.sms.bean.BaudRateResult;
import com.gosuncn.ics.sms.service.ComService;
import com.gosuncn.ics.sms.utils.ApiResultUtil;
import gnu.io.CommPortIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @apiDefine comGroup 串口接口
 */
@RestController
@RequestMapping(value = "/com")
public class ComController {
    @Autowired
    ComService comService;

    /**
     * @api {get} /sms/com/findAllComs 查找串口
     * @apiName findAllComs
     * @apiGroup comGroup
     * @apiVersion 1.0.0
     * @apiDescription 查找服务所在机器的所有串口
     * @apiSuccess (Reponse 200) {Number} code 		返回码(1--正常 其他--异常)
     * @apiSuccess (Reponse 200) {String} message 	信息提示
     * @apiSuccess (Reponse 200) {Object} data 		返回数据
     * @apiSuccess (Reponse 200) {Number} data.name	串口名称
     * @apiSuccessExample {json} Response 200 Example
     * HTTP/1.1 200 OK
     * {
     * "code":1,
     * "message": "success",
     * "data": {
     * "name":"COM1"
     * }
     * }
     */
    @GetMapping("/findAllComs")
    public ApiResult<List<CommPortIdentifier>> findAllComs() {
        return ApiResultUtil.success(comService.findAllComs());
    }

    /**
     * @api {get} /sms/com/checkSerialPort 检查串口
     * @apiName checkSerialPort
     * @apiGroup comGroup
     * @apiVersion 1.0.0
     * @apiDescription 检查串口的状态，是否可用
     * @apiParam {String}           name       串口名称
     * @apiUse CODE_200
     */
    @GetMapping("/checkSerialPort")
    public ApiResult<String> isSerialPortEnabled(@RequestParam String name) {
        boolean enabled = comService.isSerialPortEnabled(name);
        if (enabled) {
            ApiResultUtil.success("串口可用");
        }
        return ApiResultUtil.failed("没有此串口或串口被占用");
    }

    /**
     * @api {get} /sms/com/getBaudRate 获取波特率
     * @apiName getBaudRate
     * @apiGroup comGroup
     * @apiVersion 1.0.0
     * @apiDescription 获得串口的实际波特率
     * @apiParam {String}           name       串口名称
     * @apiSuccess (Reponse 200) {Number} code 		返回码(1--正常 其他--异常)
     * @apiSuccess (Reponse 200) {String} message 	信息提示
     * @apiSuccess (Reponse 200) {Object} data 		返回数据
     * @apiSuccess (Reponse 200) {Number} data.baudRate		波特率
     * @apiSuccessExample {json} Response 200 Example
     * HTTP/1.1 200 OK
     * {
     * "code":1,
     * "message": "success",
     * "data": {
     * "baudRate":115200
     * }
     * }
     */
    @GetMapping("/getBaudRate")
    public ApiResult<BaudRateResult> getBaudRate(@RequestParam String name) {
        int rate = comService.getBaudRate(name);
        if (rate == -1) {
            return ApiResultUtil.failed("获取失败");
        }
        return ApiResultUtil.success(new BaudRateResult(rate));
    }
}
