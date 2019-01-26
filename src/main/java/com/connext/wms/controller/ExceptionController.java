package com.connext.wms.controller;


import com.connext.wms.aop.OutRepoAnnotation;
import com.connext.wms.service.ExceptionService;
import com.connext.wms.service.OutRepertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    @Autowired
    private OutRepertoryService outRepertoryService;


    //查询所有异常的订单返回到列表
    @RequestMapping("/findList")
    public String findList(Model model,Integer currPage){
        model.addAttribute("page",exceptionService.selectByPage(currPage));
        return "error-order-list";
    }

    //按关键字查找相关异常的订单
    @RequestMapping("/findByKey")
    public String findByKey(@RequestParam(required = false,defaultValue = "1")Integer currPage, Model model, String key){
        model.addAttribute("page",exceptionService.selectByExampleToKey(currPage,key))
                .addAttribute("key",key);
        return "error-order-list";
    }

    //查看异常订单详情
    @RequestMapping("/toDetail")
    public String toDetail(Integer id, Model model){
        model.addAttribute("detail",exceptionService.selectByPrimaryKey(id))
                .addAttribute("good", outRepertoryService.selectListByOutRepoId(id));
        return "abnormal-order";
    }

    //对异常订单进行再次发货
    @RequestMapping("/feedback")
    @OutRepoAnnotation
    public String toDeliver(Integer outRepoId){
        exceptionService.deliver(outRepoId);
        return "redirect:findList?currPage=1";
    }

}
