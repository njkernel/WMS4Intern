package com.connext.wms.controller;

import com.connext.wms.entity.ExpressCompany;
import com.connext.wms.service.ExpressCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    //查询所有的快递公司信息显示在页面
    @RequestMapping("/findAll")
    public String findAll(Model model,int start, int size){
        List<ExpressCompany> list = expressCompanyService.selectByPage(start,size);
        model.addAttribute("list",list);
        return "";
    }

    //根据用户输入的关键字查找出符合的公司信息显示在页面
    @RequestMapping("/findByKey")
    public String findByKey(Model model,String key){
        List<ExpressCompany> list = expressCompanyService.selectByExample(key);
        model.addAttribute("list",list);
        return "";
    }

    //根据公司名称删除公司信息
    @RequestMapping("/Delete")
    public String toDelete(String expressCompanyName){
        expressCompanyService.deleteByExample(expressCompanyName);
        return "redirect:";
    }

    //修改公司信息
    @RequestMapping("/Update")
    public String toUpdate(String newName,String expressCompanyName,String contactWay){
        expressCompanyService.updateByExample(newName,expressCompanyName,contactWay);
        return "redirect:";
    }

    //添加一家公司
    @RequestMapping("/Add")
    public String toAdd(String expressCompanyName,String contactWay){
        expressCompanyService.insert(expressCompanyName,contactWay);
        return "redirect:";
    }





}
