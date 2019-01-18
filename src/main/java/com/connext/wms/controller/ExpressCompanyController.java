package com.connext.wms.controller;

import com.connext.wms.dao.ExpressCompanyMapper;
import com.connext.wms.service.ExpressCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
    @Autowired
    ExpressCompanyMapper expressCompanyMapper;


    //查询所有的快递公司信息显示在页面
    @RequestMapping("/findAll")
    public String findAll( Integer currPage, Model model){
        model.addAttribute("page",expressCompanyService.selectByPage(currPage));
        return "express-company";
    }

    //根据用户输入的关键字查找出符合的公司信息显示在页面
    @RequestMapping("/findByKey")
    public String findByKey(Integer currPage,Model model,String key) {
        model.addAttribute("page", expressCompanyService.selectByKey(1,key));
        return "express-company";
    }

    //根据公司名称删除公司信息
    @RequestMapping("/Delete")
    public String toDelete(String expressCompanyName){
        expressCompanyService.deleteByExample(expressCompanyName);
        return "redirect:findAll?currPage=1";
    }

    //修改公司信息
    @RequestMapping("/Update")
    @ResponseBody
    public Integer toUpdate(String newName,String expressCompanyName,String contactWay){
        return expressCompanyService.updateByExample(newName, expressCompanyName, contactWay);
    }

    //添加一家公司
    @RequestMapping("/Add")
    @ResponseBody
    public Integer toAdd(String expressCompanyName,String contactWay){
        return expressCompanyService.insert(expressCompanyName,contactWay);
    }


}
