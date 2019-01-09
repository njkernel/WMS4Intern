package com.connext.wms.api;

import com.connext.wms.api.dto.InRepertoryDetailDTO;
import com.connext.wms.entity.Goods;
import com.connext.wms.entity.InRepertory;
import com.connext.wms.entity.InRepertoryDetail;
import com.connext.wms.service.GoodsService;
import com.connext.wms.service.InRepertoryService;
import com.connext.wms.util.AES;
import com.connext.wms.util.Constant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Marcus
 * @Date: 2019/1/7 10:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
public class InRepertoryApi {
    private final InRepertoryService inRepertoryService;
    private final GoodsService goodsService;
    private final Constant constant;
    private ObjectMapper objectMapper;

    @Autowired
    public InRepertoryApi(InRepertoryService inRepertoryService, GoodsService goodsService, Constant constant, ObjectMapper objectMapper) {
        this.inRepertoryService = inRepertoryService;
        this.goodsService = goodsService;
        this.constant = constant;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/inRepertoryOrder")
    public void inRepertoryOrder(@RequestParam String tokens,
                                 @RequestParam String inRepoId,
                                 @RequestParam String orderId,
                                 @RequestParam String channelId,
                                 @RequestParam String expressId,
                                 @RequestParam String expressCompany,
                                 @RequestParam String detailDTOS
    ) throws IOException {
        //token校验
        if (Objects.equals(AES.AESDncode(constant.getTOKENS(), tokens), inRepoId)) {
            List<InRepertoryDetailDTO> repertoryDetailDTOS = objectMapper.readValue(detailDTOS, new TypeReference<List<InRepertoryDetailDTO>>() {
            });
            List<InRepertoryDetail> inRepertoryDetails = new ArrayList<>();
            repertoryDetailDTOS.forEach(u -> {
                Goods goods = goodsService.getGoodsBySku(u.getSku());
                inRepertoryDetails.add(new InRepertoryDetail(Integer.parseInt(inRepoId), goods.getId(), goods.getGoodsName(), u.getGoodsNum()));
            });
            Date nowTime = new Date();
            InRepertory inRepertory = new InRepertory(inRepoId, orderId, channelId, expressId, expressCompany, constant.getINIT_STATUS(), constant.getSYNC_FALSE_STATES(), constant.getRECEIVING_REPERTORY(), nowTime, constant.getREVISER(), nowTime);
            inRepertory.setRepertoryDetails(inRepertoryDetails);
            inRepertoryService.initInRepertory(inRepertory);
        }
    }
}
