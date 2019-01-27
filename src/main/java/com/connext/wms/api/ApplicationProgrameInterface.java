package com.connext.wms.api;

import com.connext.wms.api.dto.OutRepoOrderDetailDto;
import com.connext.wms.dao.GoodsMapper;
import com.connext.wms.dao.OutRepertoryDetailMapper;
import com.connext.wms.dao.OutRepertoryMapper;
import com.connext.wms.entity.*;
import com.connext.wms.service.GoodsService;
import com.connext.wms.service.OutRepertoryService;
import com.connext.wms.service.RepertoryRegulationService;
import com.connext.wms.service.TokenService;
import com.connext.wms.util.NeedToken;
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
    @Resource
    private TokenService tokenService;

    /**
     * oms推送出库单进入wms
     * @param outRepoId
     * @param orderId
     * @param channelId
     * @param receiverName
     * @param receiverAddress
     * @param expressCompany
     * @param outRepoOrderDetailDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/pushOutRepoOrder", produces = "text/json;charset=UTF-8")
    @ResponseBody
    @NeedToken
    public String pushOutRepoOrder(
            @RequestParam(required = true) String outRepoId,
            @RequestParam(required = true) String orderId,
            @RequestParam(required = true) String channelId,
            @RequestParam(required = true) String receiverName,
            @RequestParam(required = true) String receiverAddress,
            String expressCompany,
            @RequestParam(required = true) String outRepoOrderDetailDto
    ) throws IOException {
        try {
            //先将出库单插入出库单表
            OutRepertory outRepertory = new OutRepertory(outRepoId, orderId, channelId, receiverName, receiverAddress, expressCompany,new Date(),"waittingChecked","notSync");
            this.outRepertoryService.addOutRepoOrder(outRepertory);

            List<OutRepoOrderDetailDto> outRepoOrderDetailDtoList = objectMapper.readValue(outRepoOrderDetailDto,
                    new TypeReference<List<OutRepoOrderDetailDto>>() {
                    });

            //根据接收到的goodsCode查询goods
            OutRepertoryDetail outRepertoryDetail=new OutRepertoryDetail();
            List<String> skuList = new ArrayList<String>();
            List<Integer> GoodNumList = new ArrayList<>();
            System.out.println("^^^^^^^^^^^^^^"+outRepoOrderDetailDtoList);
            for (OutRepoOrderDetailDto outRepoOrderDetailDto1 : outRepoOrderDetailDtoList) {
                skuList.add(outRepoOrderDetailDto1.getGoodsCode());
                //调用 商品库存更改 方法
                Integer goodId=this.goodsService.getGoodsBySku(outRepoOrderDetailDto1.getGoodsCode()).getId();
                System.out.println("商品数量为："+outRepoOrderDetailDto1.getNum());
                this.repertoryRegulationService.deliveryGoodsBeforeDelivery(goodId,outRepoOrderDetailDto1.getNum());
                //将商品详情插到出库单详情表中
                Goods goods = this.goodsService.getGoodsBySku(outRepoOrderDetailDto1.getGoodsCode());
                outRepertoryDetail.setGoodsNum(outRepoOrderDetailDto1.getNum());
                outRepertoryDetail.setOutRepoId(outRepertory.getId());
                outRepertoryDetail.setGoodsId(goodId);
                outRepertoryDetail.setGoodsName(goods.getGoodsName());
                this.outRepertoryDetailMapper.insertSelective(outRepertoryDetail);
            }
        } catch (IOException e) {
            return "201";
        }
        return "200";
    }

    /**
     * oms取消出库单查询出库单信息
     * @param outRepoOrderNo
     * @return
     */
    @PostMapping(value = "/getOutRepoOrderStatus", produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getOutRepoOrderStatus(@RequestParam(required = true) String outRepoOrderNo) {
        return this.outRepertoryService.outRepoOrderInfo(outRepoOrderNo).getOutRepoStatus();
    }

    @PostMapping(value = "/test")
    @ResponseBody
    public String test(@RequestBody Map map){
        System.out.println(map);
        return "200";
    }


    /**
     * oms需要传入正确的账号密码获取token
     * @param map
     * @return
     */
    @PostMapping("/getToken")
    @ResponseBody
    public String getToken(@RequestBody Map map){
        String token = tokenService.getToken((String)map.get("omsname"), (String)map.get("password"));
        return token;
    }

    @NeedToken
    @RequestMapping("/testToken")
    @ResponseBody
    public String testToken(String param){
        return "ojbk";
    }


    /**
     * oms取消wms出库单，wms告知oms出库单是否取消成功
     * @param outRepoOrderNoArray
     * @return
     */
    @PostMapping("/cancelResult")
    @ResponseBody
    public String cancelResult(@RequestParam(required = true) String outRepoOrderNoArray) {
        try {
            String[] str = outRepoOrderNoArray.split(",");
            List<String> stringList = new ArrayList<String>(Arrays.asList(str));
            //防止重复取消出库单导致库存不正常的变化
            Iterator<String> outRepoOrderNo = stringList.iterator();
            while(outRepoOrderNo.hasNext()){
                String outRepoNo = outRepoOrderNo.next();
                String outRepoStatus = this.outRepertoryService.outRepoOrderInfo(outRepoNo).getOutRepoStatus();
                if(("haveCanceled").equals(outRepoStatus) || ("haveShipped").equals(outRepoStatus)){
                    outRepoOrderNo.remove();
                }else{
                    //根据出库单号更新商品库存
                    Integer outRepoId = this.outRepertoryService.outRepoOrderInfo(outRepoNo).getId();
                    for (OutRepertoryDetail outRepertoryDetail : this.outRepertoryDetailMapper.selectListByOutRepoId(outRepoId)) {
                        this.repertoryRegulationService.cancelDelivery(outRepertoryDetail.getGoodsId(), outRepertoryDetail.getGoodsNum());
                    }
                }
            }
            OutRepertory outRepertory=new OutRepertory();
            outRepertory.setOutRepoStatus("haveCanceled");
            outRepertory.setSyncStatus("haveSync");
            this.outRepertoryService.omsUpdateOutRepoOrderStatus(outRepertory, stringList);
        } catch (Exception e) {
            return "201";
        }
        return "200";
    }


}
