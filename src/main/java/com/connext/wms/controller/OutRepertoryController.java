package com.connext.wms.controller;

import com.connext.wms.entity.OutRepertory;
import com.connext.wms.entity.OutRepertoryExample;
import com.connext.wms.service.OutRepertoryService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author xiamingxing
 * @date 2019/1/7 9:26
 */
@Controller
@RequestMapping("/outRepoOrderController")
public class OutRepertoryController {
    @Resource
    private ObjectMapper objectMapper;
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
        return "out_repertory";
    }

    //分页查询展示出库单
    @RequestMapping("/outRepoOrderList")
    public String outRepoOrderList(Integer currPage, OutRepertoryExample example, Model model) {
        model.addAttribute("outRepoOrderList", this.outRepertoryService.outRepoOrderListByPage(0,10));
        return "out_repertory";
    }


    //更改出库单状态
    @RequestMapping("/updateOutRepoOrderStatus")
    @ResponseBody
    public String updateOutRepoOrderStatus(@RequestParam(required = false) String[] shippingInfo, String status, String outRepoOrderIdArray) throws IOException {
        OutRepertory outRepertory=new OutRepertory();
        outRepertory.setOutRepoStatus(status);
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(
                List.class, String.class);
        List<Integer>  list= objectMapper.readValue(outRepoOrderIdArray, javaType);
        this.outRepertoryService.updateOutRepoOrderStatus(outRepertory, list,shippingInfo);
        return "1";
    }

    //查看出库单商品详情
    @RequestMapping("/toOutRepoDetail")
    public String outRepoOrderDetail(String outRepoOrderId,Model model) {
        model.addAttribute("outRepoOrder",this.outRepertoryService.selectByOutRepoId(Integer.parseInt(outRepoOrderId)));
        model.addAttribute("outRepoOrderDetail",this.outRepertoryService.selectListByOutRepoId(Integer.parseInt(outRepoOrderId)));
        return "/specific/outstock";
    }

    //WMS批量取消出库单
    @RequestMapping("/cancelOutRepoOrder")
    public String cancelOutRepoOrder(String[] outRepoOrderNo){
        this.outRepertoryService.cancelOutRepoOrder(outRepoOrderNo);
        return "";
    }

}
