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
import java.util.ArrayList;
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

    //分页查询展示出库单
    @RequestMapping("/outRepoOrderList")
    public String outRepoOrderList(Integer currPage, Model model) {
        model.addAttribute("page", this.outRepertoryService.outRepoOrderList(currPage));
        return "out_repertory";
    }

    //模糊查询
    @RequestMapping("/unclearSelect")
    public String unclearSelect(Integer currPage, String selectStatus, String outRepoOrderId, Model model) {
        System.out.println(selectStatus+"^^^^^^"+outRepoOrderId);
        model.addAttribute("page", this.outRepertoryService.unclearSelect(outRepoOrderId, selectStatus, currPage));
        return "out_repertory";
    }


    //更改出库单状态
    @RequestMapping("/updateOutRepoOrderStatus")
    @ResponseBody
    public String updateOutRepoOrderStatus(String status, String outRepoOrderIdArray) throws IOException {
        try {
            OutRepertory outRepertory = new OutRepertory();
            outRepertory.setOutRepoStatus(status);
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(
                    List.class, String.class);
            List<Integer> list = objectMapper.readValue(outRepoOrderIdArray, javaType);
            this.outRepertoryService.updateOutRepoOrderStatus(outRepertory, list);
        } catch (IOException e) {
            return "20190107";
        }
        return "20190101";
    }

    //查看出库单商品详情
    @RequestMapping("/toOutRepoDetail")
    public String outRepoOrderDetail(String outRepoOrderId, Model model) {
        model.addAttribute("outRepoOrder", this.outRepertoryService.selectByOutRepoId(Integer.parseInt(outRepoOrderId)));
        model.addAttribute("outRepoOrderDetail", this.outRepertoryService.selectListByOutRepoId(Integer.parseInt(outRepoOrderId)));
        return "/specific/outstock";
    }

    //发货时可以修改出库单的信息（添加出库单信息）,这是跳转到更新页面
    @RequestMapping("/preUpdateOutRepoOrder")
    public String preUpdateOutRepoOrder(String outRepoOrderId, Model model) {
        model.addAttribute("outRepoOrder", this.outRepertoryService.selectByOutRepoId(Integer.parseInt(outRepoOrderId)));
        model.addAttribute("outRepoOrderDetail", this.outRepertoryService.selectListByOutRepoId(Integer.parseInt(outRepoOrderId)));
        return "/specific/outstock_update";
    }

    //发货时更新发货信息
    @RequestMapping("/updateOutRepoOrder")
    public String updateOutRepoOrder(OutRepertory outRepertory) {
        this.outRepertoryService.updateOutRepoOrder(outRepertory);
        return "redirect:/outRepoOrderController/outRepoOrderList";
    }


    //WMS批量取消出库单
    @RequestMapping("/cancelOutRepoOrder")
    @ResponseBody
    public String cancelOutRepoOrder(String outRepoOrderNoArray) throws IOException {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(
                    List.class, String.class);
            List<String> list = objectMapper.readValue(outRepoOrderNoArray, javaType);
            String[] stringArray = list.toArray(new String[list.size()]);
            this.outRepertoryService.cancelOutRepoOrder(stringArray);
        } catch (IOException e) {
            return "20190107";
        }
        return "20190101";
    }


}
