package com.connext.wms.api;

import com.connext.wms.api.dto.OutRepoOrderDetailDto;
import com.connext.wms.dao.GoodsMapper;
import com.connext.wms.dao.OutRepertoryDetailMapper;
import com.connext.wms.dao.OutRepertoryMapper;
import com.connext.wms.entity.*;
import com.connext.wms.service.GoodsService;
import com.connext.wms.service.OutRepertoryService;
import com.connext.wms.service.RepertoryRegulationService;
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
    private RepertoryRegulationService repertoryRegulationService;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private OutRepertoryService outRepertoryService;
    @Resource
    private OutRepertoryDetailMapper outRepertoryDetailMapper;
    @Resource
    private GoodsService goodsService;

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
        //先将出库单插入出库单表
        OutRepertory outRepertory = new OutRepertory(outRepoId, orderId, channelId, receiverName, receiverAddress, expressCompany,new Date(),"waittingChecked");
        this.outRepertoryService.addOutRepoOrder(outRepertory);
        //出库单插入表中获取新增字段的主键
        System.out.println("*******************"+outRepertory.getId());

        List<OutRepoOrderDetailDto> outRepoOrderDetailDtoList = objectMapper.readValue(outRepoOrderDetailDto,
                new TypeReference<List<OutRepoOrderDetailDto>>() {
        });

        //根据接收到的goodsCode查询goods
        OutRepertoryDetail outRepertoryDetail=new OutRepertoryDetail();
        List<String> skuList = new ArrayList<String>();
        System.out.println("^^^^^^^^^^^^^^"+outRepoOrderDetailDtoList);
        for (OutRepoOrderDetailDto outRepoOrderDetailDto1 : outRepoOrderDetailDtoList) {
            System.out.println("++++++++++++++++"+outRepoOrderDetailDto1.getGoodsCode());
            skuList.add(outRepoOrderDetailDto1.getGoodsCode());
            //调用 商品库存更改 方法
            Integer goodId=this.goodsService.getGoodsBySku(outRepoOrderDetailDto1.getGoodsCode()).getId();
            System.out.println(outRepoOrderDetailDto1.getNum()+"##########");
            this.repertoryRegulationService.deliveryGoodsBeforeDelivery(goodId,outRepoOrderDetailDto1.getNum());
            outRepertoryDetail.setGoodsNum(outRepoOrderDetailDto1.getNum());
        }
        for(Goods good:this.goodsService.getGoodsBySkuList(skuList)){
            System.out.println("##########"+good);
            outRepertoryDetail.setGoodsName(good.getGoodsName());
            outRepertoryDetail.setGoodsId(good.getId());
            outRepertoryDetail.setOutRepoId(outRepertory.getId());
            this.outRepertoryDetailMapper.insertSelective(outRepertoryDetail);
        }
        return "200";
    }

    //oms取消出库单查询出库单信息
    @PostMapping(value = "/getOutRepoOrderStatus", produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getOutRepoOrderStatus(@RequestParam(required = true) String outRepoOrderNo) {
        return this.outRepertoryService.outRepoOrderInfo(outRepoOrderNo).getOutRepoStatus();
    }


    //oms取消wms出库单，wms告知oms出库单是否取消成功
    @PostMapping("/cancelResult")
    @ResponseBody
    public String cancelResult(@RequestParam(required = true) String outRepoOrderNo) {
        try {
            String[] str = outRepoOrderNo.split(",");
            List<String> integerList = Arrays.asList(str);
            OutRepertory outRepertory=new OutRepertory();
            outRepertory.setOutRepoStatus("已取消");
            this.outRepertoryService.omsUpdateOutRepoOrderStatus(outRepertory, integerList);
        } catch (Exception e) {
            return "201";
        }
        return "200";
    }

}
