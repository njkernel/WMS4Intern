package com.connext.wms.controller;

import com.connext.wms.entity.Goods;
import com.connext.wms.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;


    //分页查询所有商品
    @RequestMapping("/findAll")
    public String toList(Model model, Integer currPage) {
        model.addAttribute("page", goodsService.findAll(currPage));
        return "goods-list";
    }

    //关键字查询
    @RequestMapping("/byKey")
    public String Key(Model model, String key) {
        model.addAttribute("page", goodsService.selectByExample(key));
        return "goods-list";
    }


    //修改商品信息
    @ResponseBody
    @RequestMapping("/update")
    public String updateGoods(Goods goods) {
        return goodsService.updateGoodsNameAndPrice(goods);
    }

    /**
     * 显示编辑页面的商品信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getGoodsById")
    public Goods getGoodsById(Integer id) {
        Goods goods = goodsService.getGoodsById(id);
        return goods;
    }


}
