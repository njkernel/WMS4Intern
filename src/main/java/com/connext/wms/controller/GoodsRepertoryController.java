package com.connext.wms.controller;

import com.connext.wms.entity.RealRepertoryVO;
import com.connext.wms.service.GoodsRepertoryService;
import com.connext.wms.service.RepertoryRegulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/14 10:50
 */
@Controller
@RequestMapping("/goodsRepertory")
public class GoodsRepertoryController {
    @Autowired
    private GoodsRepertoryService goodsRepertoryService;

    @Autowired
    private RepertoryRegulationService repertoryRegulationService;

    //跳转index界面
    @RequestMapping("/toIndex")
    public String toIndex() {
        return "index";
    }

    //分页查询商品库存
    @RequestMapping("/showRealRepertory")
    public String showRealRepertoryPage(Integer currPage, Model model) {
        model.addAttribute("page", goodsRepertoryService.showRealRepertory(currPage));
        return "wareHouse";
    }

    //补货操作
    @ResponseBody
    @RequestMapping("/replenishRepertory")
    public String replenishRepertory(Integer id, Integer num) {
        repertoryRegulationService.replenishRepertory(id, num);
        return "success";
    }

    //根据输入的商品名称的关键字查询商品库存
    @RequestMapping("/findByKey")
    public String findByKey(Model model, String key) {
        model.addAttribute("page", goodsRepertoryService.getGoodsRepertoryByGoodsName(key));
        return "wareHouse";
    }
}
