package com.connext.wms.controller;


import com.connext.wms.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Chao.Sun
 * @Date: 2019/1/7 9:37
 * @Version 1.0
 */
@Controller
@RequestMapping("/exception")
public class ExceptionController {
    @Autowired
    private ExceptionService exceptionService;


    //查询所有异常的订单返回到列表
    @RequestMapping("/findList")
    public String findList(Model model,Integer currPage){
        model.addAttribute("page",exceptionService.selectByPage(currPage));
        return "error-order-list";
    }

    //按关键字查找相关异常的订单
    @RequestMapping("/findByKey")
    public String findByKey(Integer currPage,Model model,String key){
        model.addAttribute("page",exceptionService.selectByExampleToKey(1,key));
        return "error-order-list";
    }

    //查看异常订单详情
    @RequestMapping("/toDetail")
    public String toDetail(Integer id, Model model){
        model.addAttribute("detail",exceptionService.selectByPrimaryKey(id));
        return "abnormal-order";
    }

    //对异常订单进行再次发货
    @RequestMapping("/feedback")
    public String toDeliver(){
        //调用出库service中的反馈发货信息方法，对在异常列表的订单进行再次信息反馈
        return "发货信息";
    }



}
