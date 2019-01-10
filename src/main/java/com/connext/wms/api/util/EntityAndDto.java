package com.connext.wms.api.util;

import com.connext.wms.api.dto.InRepertoryDetailDTO;
import com.connext.wms.api.dto.InputFeedbackDetail;
import com.connext.wms.entity.Goods;
import com.connext.wms.entity.InRepertory;
import com.connext.wms.entity.InRepertoryDetail;
import com.connext.wms.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2019/1/10 12:51
 * @Version 1.0
 */
@Component
public class EntityAndDto {
    private final GoodsService goodsService;

    @Autowired
    public EntityAndDto(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public List<InRepertoryDetail> toEntity(String inRepoId, List<InRepertoryDetailDTO> repertoryDetailDTOS) {
        List<InRepertoryDetail> inRepertoryDetails = new ArrayList<>();
        repertoryDetailDTOS.forEach(u -> {
            Goods goods = goodsService.getGoodsBySku(u.getSku());
            inRepertoryDetails.add(new InRepertoryDetail(Integer.parseInt(inRepoId), goods.getId(), goods.getGoodsName(), u.getGoodsNum()));
        });
        return inRepertoryDetails;
    }

    public List<InputFeedbackDetail> toDTO(InRepertory inRepertory) {
        List<InputFeedbackDetail> list = new ArrayList<>();
        inRepertory.getRepertoryDetails().forEach(u -> {
            Goods goods = goodsService.getGoodsById(u.getGoodsId());
            list.add(new InputFeedbackDetail(goods.getSku(),String.valueOf(u.getGoodsNum())));
        });
        return list;
    }
}
