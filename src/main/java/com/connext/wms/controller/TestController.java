package com.connext.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
public class TestController {

    @Autowired
    private MessageSource messageSource;

    //用来测试国际化是否成功
    @RequestMapping("/test")
    public String index(Model model) {
        Locale locale = LocaleContextHolder.getLocale();
        model.addAttribute("exception", messageSource.getMessage("exception", null, locale));
        return "index";
    }

}
