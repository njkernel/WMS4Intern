package com.connext.wms.controller;

import com.connext.wms.entity.ExpressCompany;
import com.connext.wms.service.ExpressCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Locale;

/**
 * @Author: Chao.Sun
 * @Date: 2019/1/2 11:25
 * @Version 1.0
 */
@Controller
@RequestMapping("/expressCompany")
public class ExpressCompanyController {
    @Autowired
    private ExpressCompanyService expressCompanyService;
/*    @Autowired
    private MessageSource messageSource;*/


    //查询所有的快递公司信息显示在页面
    @RequestMapping("/findAll")
    public String findAll(Model model){
        model.addAttribute("express",expressCompanyService.selectByPage(1,5));
/*        Locale locale = LocaleContextHolder.getLocale();
        model.addAttribute("exception", messageSource.getMessage("exception", null, locale));*/
        return "express-company";
    }

    //根据用户输入的关键字查找出符合的公司信息显示在页面
    @RequestMapping("/findByKey")
    public String findByKey(Model model,String key) {
        //1:没有查找到对应关键字的记录
        List<ExpressCompany> list = expressCompanyService.selectByExample(key);
        if (list.size()==0) {
            return "1";
        } else {
            System.out.println(list);
            model.addAttribute("express", list);
            return "express-company";
        }
    }

    //根据公司名称删除公司信息
    @RequestMapping("/Delete")
    public String toDelete(String expressCompanyName){
        System.out.println(expressCompanyName);
        expressCompanyService.deleteByExample(expressCompanyName);
        return "redirect:findAll";
    }

    //修改公司信息
    @RequestMapping("/Update")
    public String toUpdate(String newName,String expressCompanyName,String contactWay){
        expressCompanyService.updateByExample(newName,expressCompanyName,contactWay);
        return "redirect:findAll";
    }

    //添加一家公司
    @RequestMapping("/Add")
    public String toAdd(String expressCompanyName,String contactWay){
        expressCompanyService.insert(expressCompanyName,contactWay);
        return "redirect:findAll";
    }





}
