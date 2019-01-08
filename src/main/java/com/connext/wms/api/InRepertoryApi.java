package com.connext.wms.api;

import com.connext.wms.api.dto.InRepertoryDetailDTO;
import com.connext.wms.entity.Goods;
import com.connext.wms.entity.InRepertory;
import com.connext.wms.entity.InRepertoryDetail;
import com.connext.wms.service.GoodsService;
import com.connext.wms.service.InRepertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2019/1/7 10:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
public class InRepertoryApi {
    private final String INREPOSTATES = "待收货";
    private final String SYNCSTATES = "false";
    private final String RECEIVINGREPO = "南京仓";
    private final String REVISER = "Marcus";
    private final String TOKENS = "PkQMUE1T9ydEDpsU";
    private final InRepertoryService inRepertoryService;
    private final GoodsService goodsService;

    @Autowired
    public InRepertoryApi(InRepertoryService inRepertoryService, GoodsService goodsService) {
        this.inRepertoryService = inRepertoryService;
        this.goodsService = goodsService;
    }

    @PostMapping("/inRepertoryOrder")
    public String inRepertoryOrder(@RequestParam String tokens,
                                   @RequestParam String inRepoId,
                                   @RequestParam String orderId,
                                   @RequestParam String channelId,
                                   @RequestParam String expressId,
                                   @RequestParam String expressCompany,
                                   @RequestParam(required = false) List<InRepertoryDetailDTO> repertoryDetailDTOS
    ) {
        if (!TOKENS.equals(tokens)) {
            return "Tokens error!";
        }
        List<InRepertoryDetail> inRepertoryDetails = new ArrayList<>();
//        repertoryDetailDTOS.forEach(u -> {
//                    //尚未实装
//                    Goods goods = goodsService.getGoodsBySku(u.getSku());
//                    inRepertoryDetails.add(new InRepertoryDetail(Integer.parseInt(inRepoId), goods.getId(), goods.getGoodsName(), u.getGoodsNum()));
//                });
        Date nowTime = new Date();
        InRepertory inRepertory = new InRepertory(inRepoId, orderId, channelId, expressId, expressCompany, INREPOSTATES, SYNCSTATES, RECEIVINGREPO, nowTime, REVISER, nowTime);
        inRepertory.setRepertoryDetails(inRepertoryDetails);
        inRepertoryService.initInRepertory(inRepertory);
        return "Success";
    }
}
