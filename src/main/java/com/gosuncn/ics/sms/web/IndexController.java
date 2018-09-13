package com.gosuncn.ics.sms.web;

import com.gosuncn.ics.sms.common.ConstantValue;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/")
public class IndexController {

    @GetMapping
    public ModelAndView indexPage(ModelMap map){
        map.put("version", ConstantValue.version);
        return new ModelAndView("index");
    }
}
