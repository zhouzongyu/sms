define({ "api": [
  {
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "varname1",
            "description": "<p>No type.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "varname2",
            "description": "<p>With type.</p>"
          }
        ]
      }
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "../out/production/resources/static/apidoc/main.js",
    "group": "G__projects_ICS_IoT_service_cloud_services_sms_out_production_resources_static_apidoc_main_js",
    "groupTitle": "G__projects_ICS_IoT_service_cloud_services_sms_out_production_resources_static_apidoc_main_js",
    "name": ""
  },
  {
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "varname1",
            "description": "<p>No type.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "varname2",
            "description": "<p>With type.</p>"
          }
        ]
      }
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "../src/main/resources/static/apidoc/main.js",
    "group": "G__projects_ICS_IoT_service_cloud_services_sms_src_main_resources_static_apidoc_main_js",
    "groupTitle": "G__projects_ICS_IoT_service_cloud_services_sms_src_main_resources_static_apidoc_main_js",
    "name": ""
  },
  {
    "type": "get",
    "url": "/sms/com/checkSerialPort",
    "title": "检查串口",
    "name": "checkSerialPort",
    "group": "comGroup",
    "version": "1.0.0",
    "description": "<p>检查串口的状态，是否可用</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>串口名称</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/gosuncn/ics/sms/web/ComController.java",
    "groupTitle": "串口接口",
    "sampleRequest": [
      {
        "url": "http://192.168.39.128:8311/sms/com/checkSerialPort"
      }
    ],
    "success": {
      "fields": {
        "Reponse 200": [
          {
            "group": "Reponse 200",
            "type": "Number",
            "optional": false,
            "field": "code",
            "description": "<p>返回码(1--正常 其他--异常)</p>"
          },
          {
            "group": "Reponse 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息提示</p>"
          },
          {
            "group": "Reponse 200",
            "type": "json",
            "optional": true,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Response 200 Example ",
          "content": "HTTP/1.1 200 OK \n{\n\"code\":1, \n\"message\": \"success\", \n\"data\": \"\"\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/sms/com/findAllComs",
    "title": "查找串口",
    "name": "findAllComs",
    "group": "comGroup",
    "version": "1.0.0",
    "description": "<p>查找服务所在机器的所有串口</p>",
    "success": {
      "fields": {
        "Reponse 200": [
          {
            "group": "Reponse 200",
            "type": "Number",
            "optional": false,
            "field": "code",
            "description": "<p>返回码(1--正常 其他--异常)</p>"
          },
          {
            "group": "Reponse 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息提示</p>"
          },
          {
            "group": "Reponse 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          },
          {
            "group": "Reponse 200",
            "type": "Number",
            "optional": false,
            "field": "data.name",
            "description": "<p>串口名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Response 200 Example",
          "content": "HTTP/1.1 200 OK\n{\n\"code\":1,\n\"message\": \"success\",\n\"data\": {\n\"name\":\"COM1\"\n}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "../src/main/java/com/gosuncn/ics/sms/web/ComController.java",
    "groupTitle": "串口接口",
    "sampleRequest": [
      {
        "url": "http://192.168.39.128:8311/sms/com/findAllComs"
      }
    ]
  },
  {
    "type": "get",
    "url": "/sms/com/getBaudRate",
    "title": "获取波特率",
    "name": "getBaudRate",
    "group": "comGroup",
    "version": "1.0.0",
    "description": "<p>获得串口的实际波特率</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>串口名称</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Reponse 200": [
          {
            "group": "Reponse 200",
            "type": "Number",
            "optional": false,
            "field": "code",
            "description": "<p>返回码(1--正常 其他--异常)</p>"
          },
          {
            "group": "Reponse 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息提示</p>"
          },
          {
            "group": "Reponse 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          },
          {
            "group": "Reponse 200",
            "type": "Number",
            "optional": false,
            "field": "data.baudRate",
            "description": "<p>波特率</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Response 200 Example",
          "content": "HTTP/1.1 200 OK\n{\n\"code\":1,\n\"message\": \"success\",\n\"data\": {\n\"baudRate\":115200\n}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "../src/main/java/com/gosuncn/ics/sms/web/ComController.java",
    "groupTitle": "串口接口",
    "sampleRequest": [
      {
        "url": "http://192.168.39.128:8311/sms/com/getBaudRate"
      }
    ]
  },
  {
    "type": "get",
    "url": "/sms/msg/sendTemplate",
    "title": "模板发送短信",
    "name": "______",
    "group": "smsGroup",
    "version": "1.0.0",
    "description": "<p>当使用aliyun发送短信时可以使用此接口，假如模板为“hello:${name}”,那么模板参数应为json：{&quot;name&quot;:&quot;Tom&quot;}</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "signName",
            "description": "<p>短信签名，从短信服务商获取，如果不传，则采用默认值</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "templateCode",
            "description": "<p>短信模板，从短信服务商获取</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "templateParam",
            "description": "<p>模板参数，根据实际的短信服务商说明设置</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/gosuncn/ics/sms/web/SmsController.java",
    "groupTitle": "短信接口",
    "sampleRequest": [
      {
        "url": "http://192.168.39.128:8311/sms/msg/sendTemplate"
      }
    ],
    "success": {
      "fields": {
        "Reponse 200": [
          {
            "group": "Reponse 200",
            "type": "Number",
            "optional": false,
            "field": "code",
            "description": "<p>返回码(1--正常 其他--异常)</p>"
          },
          {
            "group": "Reponse 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息提示</p>"
          },
          {
            "group": "Reponse 200",
            "type": "json",
            "optional": true,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Response 200 Example ",
          "content": "HTTP/1.1 200 OK \n{\n\"code\":1, \n\"message\": \"success\", \n\"data\": \"\"\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/sms/msg/send",
    "title": "短信猫发送短信",
    "name": "_______",
    "group": "smsGroup",
    "version": "1.0.0",
    "description": "<p>当采用短信猫发送时可以使用此接口</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>发送内容</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/gosuncn/ics/sms/web/SmsController.java",
    "groupTitle": "短信接口",
    "sampleRequest": [
      {
        "url": "http://192.168.39.128:8311/sms/msg/send"
      }
    ],
    "success": {
      "fields": {
        "Reponse 200": [
          {
            "group": "Reponse 200",
            "type": "Number",
            "optional": false,
            "field": "code",
            "description": "<p>返回码(1--正常 其他--异常)</p>"
          },
          {
            "group": "Reponse 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息提示</p>"
          },
          {
            "group": "Reponse 200",
            "type": "json",
            "optional": true,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Response 200 Example ",
          "content": "HTTP/1.1 200 OK \n{\n\"code\":1, \n\"message\": \"success\", \n\"data\": \"\"\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/sda/test/:content",
    "title": "测试,请忽略",
    "name": "test",
    "group": "testGroup",
    "version": "1.0.0",
    "description": "<p>测试，请忽略</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "content",
            "defaultValue": "hello",
            "description": "<p>测试内容+默认值</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "optional",
            "description": "<p>可选项</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "optional2",
            "defaultValue": "123",
            "description": "<p>可选项+默认值</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": false,
            "field": "value1",
            "description": "<p>布尔值</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "value2",
            "description": "<p>数字</p>"
          },
          {
            "group": "Parameter",
            "type": "Object",
            "optional": false,
            "field": "value3",
            "description": "<p>对象</p>"
          },
          {
            "group": "Parameter",
            "type": "String[]",
            "optional": false,
            "field": "value4",
            "description": "<p>字符串数组</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "size": "..5",
            "optional": false,
            "field": "value5",
            "description": "<p>最大长度为5</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "size": "2..5",
            "optional": false,
            "field": "value6",
            "description": "<p>长度必须在2和5之间</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "size": "100-999",
            "optional": false,
            "field": "value7",
            "description": "<p>范围在100到999之间</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "allowedValues": [
              "1",
              "2",
              "3",
              "99"
            ],
            "optional": false,
            "field": "value8",
            "description": "<p>只能在1,2,3,99中选择</p>"
          }
        ]
      }
    },
    "filename": "../src/main/java/com/gosuncn/ics/sms/web/TestController.java",
    "groupTitle": "测试",
    "sampleRequest": [
      {
        "url": "http://192.168.39.128:8311/sda/test/:content"
      }
    ],
    "success": {
      "fields": {
        "Reponse 200": [
          {
            "group": "Reponse 200",
            "type": "Number",
            "optional": false,
            "field": "code",
            "description": "<p>返回码(1--正常 其他--异常)</p>"
          },
          {
            "group": "Reponse 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息提示</p>"
          },
          {
            "group": "Reponse 200",
            "type": "json",
            "optional": true,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Response 200 Example ",
          "content": "HTTP/1.1 200 OK \n{\n\"code\":1, \n\"message\": \"success\", \n\"data\": \"\"\n}",
          "type": "json"
        }
      ]
    }
  }
] });
