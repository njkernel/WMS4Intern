package com.connext.wms.api;

import com.connext.wms.api.dto.OutRepoOrderDetailDto;
import com.connext.wms.dao.GoodsMapper;
import com.connext.wms.dao.OutRepertoryMapper;
import com.connext.wms.entity.*;
import com.connext.wms.service.OutRepertoryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @author xiamingxing
 * @date 2019/1/7 16:06
 */
@Controller
@RequestMapping("/api")
public class ApplicationProgrameInterface {
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private OutRepertoryService outRepertoryService;
    @Resource
    private OutRepertory outRepertory;

    //oms推送出库单进入wms
    @PostMapping(value = "/pushOutRepoOrder", produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String pushOutRepoOrder(
            @RequestParam(required = true) String outRepoId,
            @RequestParam(required = true) String orderId,
            @RequestParam(required = true) String channelId,
            @RequestParam(required = true) String receiverName,
            @RequestParam(required = true) String receiverAddress,
            String expressCompany,
            @RequestParam(required = true) String outRepoOrderDetailDto
    ) throws IOException {
        List<OutRepoOrderDetailDto> outRepoOrderDetailDtoList = objectMapper.readValue(outRepoOrderDetailDto,
                new TypeReference<List<OutRepoOrderDetailDto>>() {
                });

        List<String> skuList = new ArrayList<String>();
        for (OutRepoOrderDetailDto outRepoOrderDetailDto1 : outRepoOrderDetailDtoList) {
            skuList.add(outRepoOrderDetailDto1.getGoodsCode());
            System.out.println(outRepoOrderDetailDto1.getGoodsCode());
            OutRepertoryDetail outRepertoryDetail = new OutRepertoryDetail();
            outRepertoryDetail.setGoodsNum(outRepoOrderDetailDto1.getNum());
        }
            /*goodsExample.createCriteria().andSkuIn(skuList);
            this.goodsMapper.selectByExample(goodsExample);*/
        outRepertory = new OutRepertory(outRepoId, orderId, channelId, receiverName, receiverAddress, expressCompany);
        System.out.println(outRepertory);
        this.outRepertoryService.addOutRepoOrder(outRepertory);
        return "200";
    }

    //oms取消出库单查询出库单信息
    @PostMapping(value = "/getOutRepoOrderStatus", produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getOutRepoOrderStatus(@RequestParam(required = true) String outRepoOrderNo) {
        System.out.println("***"+outRepoOrderNo);
        return this.outRepertoryService.outRepoOrderInfo(outRepoOrderNo).getOutRepoStatus();
    }

    //测试自己的调用接口的方法是否奏效
    /*@PostMapping(value="/test")
    @ResponseBody
    public String test(@RequestBody Map map){
        System.out.println(map);
        return "200";
    }*/


    //oms取消wms出库单，wms告知oms出库单是否取消成功
    @PostMapping("/cancelResult")
    @ResponseBody
    public String cancelResult(@RequestParam(required = true) String outRepoOrderNo) {
        try {
            System.out.println(outRepoOrderNo);
            String[] str = outRepoOrderNo.split(",");
            List<String> integerList = Arrays.asList(str);
            outRepertory.setOutRepoStatus("已取消");
            this.outRepertoryService.omsUpdateOutRepoOrderStatus(outRepertory, integerList);
        } catch (Exception e) {
            return "201";
        }
        return "200";
    }

}
