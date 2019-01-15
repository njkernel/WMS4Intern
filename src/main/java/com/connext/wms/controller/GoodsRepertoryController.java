package com.connext.wms.controller;

import com.connext.wms.entity.RealRepertoryVO;
import com.connext.wms.service.GoodsRepertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    //分页查询商品库存
    @RequestMapping("/showRealRepertory")
    public String showRealRepertoryPage(Integer start, Integer size, Model model) {
        List<RealRepertoryVO> realRepertoryVOList = goodsRepertoryService.showRealRepertory(start, size);
        model.addAttribute(realRepertoryVOList);
        return "wareHouse";
    }

    //补货操作
    @RequestMapping("/replenishRepertory")
    public String replenishRepertory() {

        return "redirect:/goodsRepertory/showRealRepertory";
    }
}
