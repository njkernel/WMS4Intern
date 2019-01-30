package com.connext.wms.controller;

import com.connext.wms.aop.OutRepoAnnotation;
import com.connext.wms.entity.OutRepertory;
import com.connext.wms.service.ExpressCompanyService;
import com.connext.wms.service.OutRepertoryService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Resource
    private ExpressCompanyService expressCompanyService;

    /**
     * 分页查询展示出库单
     *
     * @param currPage
     * @param model
     * @return String
     */
    @RequestMapping("/outRepoOrderList")
    public String outRepoOrderList(Integer currPage, Model model) {
        model.addAttribute("page", this.outRepertoryService.outRepoOrderList(currPage));
        return "out_repertory";
    }

    /**
     * 模糊查询
     *
     * @param currPage
     * @param selectStatus
     * @param outRepoOrderId
     * @param model
     * @return String
     */
    @RequestMapping("/unclearSelect")
    public String unclearSelect(Integer currPage, String selectStatus, String outRepoOrderId, Model model) {
        model.addAttribute("selectStatus", selectStatus);
        model.addAttribute("outRepoOrderId", outRepoOrderId);
        model.addAttribute("page", this.outRepertoryService.unclearSelect(outRepoOrderId, selectStatus, currPage));
        return "out_repertory";
    }

    /**
     * 跳转值加载界面
     *
     * @param currPage
     * @param model
     * @return String
     */
    @RequestMapping("/toLoad")
    public String toLoad(Integer currPage, Model model) {
        model.addAttribute("currPage", currPage);
        return "loading_data";
    }


    /**
     * 更改出库单状态
     *
     * @param status
     * @param outRepoOrderIdArray
     * @return String
     * @throws IOException
     */
    @RequestMapping("/updateOutRepoOrderStatus")
    @ResponseBody
    public String updateOutRepoOrderStatus(String status, String outRepoOrderIdArray) throws IOException {
        OutRepertory outRepertory = new OutRepertory();
        outRepertory.setOutRepoStatus(status);
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(
                List.class, String.class);
        List<Integer> list = objectMapper.readValue(outRepoOrderIdArray, javaType);
        return this.outRepertoryService.updateOutRepoOrderStatus(outRepertory, list);
    }

    /**
     * 查看出库单商品详情
     *
     * @param outRepoOrderId
     * @param model
     * @return String
     */
    @RequestMapping("/toOutRepoDetail")
    public String outRepoOrderDetail(String outRepoOrderId, Model model) {
        model.addAttribute("outRepoOrder", this.outRepertoryService.selectByOutRepoId(Integer.parseInt(outRepoOrderId)));
        model.addAttribute("outRepoOrderDetail", this.outRepertoryService.selectListByOutRepoId(Integer.parseInt(outRepoOrderId)));
        return "specific/outstock";
    }

    /**
     * 发货时可以修改出库单的信息（添加出库单信息）,这是跳转到更新页面
     *
     * @param outRepoOrderId
     * @param model
     * @return String
     */
    @RequestMapping("/preUpdateOutRepoOrder")
    public String preUpdateOutRepoOrder(String outRepoOrderId, Model model) {
        model.addAttribute("outRepoOrder", this.outRepertoryService.selectByOutRepoId(Integer.parseInt(outRepoOrderId)));
        model.addAttribute("outRepoOrderDetail", this.outRepertoryService.selectListByOutRepoId(Integer.parseInt(outRepoOrderId)));
        model.addAttribute("expressCompany", this.expressCompanyService.findAll());
        return "specific/outstock_update";
    }

    /**
     * 发货时更新发货信息
     *
     * @param outRepertory
     * @return String
     */
    @RequestMapping("/updateOutRepoOrder")
    public String updateOutRepoOrder(OutRepertory outRepertory) {
        System.out.println("填写的发货信息有："+outRepertory.toString());
        this.outRepertoryService.updateOutRepoOrder(outRepertory);
        return "redirect:/outRepoOrderController/outRepoOrderList";
    }


    /**
     * WMS批量取消出库单
     *
     * @param outRepoOrderNoArray
     * @return String
     * @throws IOException
     */
    @RequestMapping("/cancelOutRepoOrder")
    @ResponseBody
    public String cancelOutRepoOrder(String outRepoOrderNoArray) throws IOException {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(
                List.class, String.class);
        List<String> list = objectMapper.readValue(outRepoOrderNoArray, javaType);
        String[] stringArray = list.toArray(new String[list.size()]);
        return this.outRepertoryService.cancelOutRepoOrder(stringArray);

    }


}
