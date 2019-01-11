package com.connext.wms.controller;

import com.connext.wms.entity.OutRepertory;
import com.connext.wms.entity.OutRepertoryExample;
import com.connext.wms.service.OutRepertoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiamingxing
 * @date 2019/1/7 9:26
 */
@Controller
@RequestMapping("/outRepoOrderController")
public class OutRepertoryController {
    @Resource
    private OutRepertoryService outRepertoryService;

    //跳转index界面
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";
    }

    //index跳转outRepo界面
    @RequestMapping("/toOutRepoOrder")
    public String toOutRepoOrder(){
        return "warehouse-out-list";
    }

    //分页查询展示出库单
    @RequestMapping("/outRepoOrderList")
    public String outRepoOrderList(Integer currPage, OutRepertoryExample example, Model model) {
       // model.addAttribute("outRepoOrderList", this.outRepertoryService.outRepoOrderList(currPage, example));
        return "warehouse-out-list";
    }

    //更改出库单状态
    @RequestMapping("/updateOutRepoOrderStatus")
    public String updateOutRepoOrderStatus(@RequestParam(required = false) String[] shippingInfo, OutRepertory outRepertory, List<Integer> outRepoOrderIdList) {
        this.outRepertoryService.updateOutRepoOrderStatus(outRepertory, outRepoOrderIdList,shippingInfo);
        return "";
    }

    //查看出库单商品详情
    @RequestMapping("/outRepoOrderDetail")
    public String outRepoOrderDetail(String outRepoOrderId) {
        this.outRepertoryService.outRepoOrderDetail(outRepoOrderId);
        return "";
    }

    //WMS批量取消出库单
    @RequestMapping("/cancelOutRepoOrder")
    public String cancelOutRepoOrder(String[] outRepoOrderNo){
        this.outRepertoryService.cancelOutRepoOrder(outRepoOrderNo);
        return "";
    }

}
