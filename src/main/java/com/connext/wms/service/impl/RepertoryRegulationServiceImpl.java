package com.connext.wms.service.impl;

import com.connext.wms.dao.GoodsRepertoryMapper;
import com.connext.wms.dao.RepertoryRegulationMapper;
import com.connext.wms.entity.InRepertory;
import com.connext.wms.service.GoodsRepertoryService;
import com.connext.wms.service.RepertoryRegulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/7 15:00
 */
@Service
public class RepertoryRegulationServiceImpl implements RepertoryRegulationService {
    @Autowired
    RepertoryRegulationMapper repertoryRegulationMapper;
    @Autowired
    GoodsRepertoryMapper goodsRepertoryMapper;
    @Override
    public void deliveryGoodsBeforeDelivery(Integer goodsId,Integer num) {
        Integer id = goodsRepertoryMapper.getIdByGoodsId(goodsId).getId();
        repertoryRegulationMapper.addLockedRepertory(id,num);
        repertoryRegulationMapper.reduceAvailableRepertory(id,-num);
    }

    @Override
    public void deliveryGoodsAfterDelivery(Integer goodsId,Integer num) {
        Integer id = goodsRepertoryMapper.getIdByGoodsId(goodsId).getId();
        repertoryRegulationMapper.reduceLockedRepertory(id,-num);
        repertoryRegulationMapper.reduceTotalRepertory(id,-num);
    }

    @Override
    public void rejectedGoodsSuccess(Integer goodsId,Integer num) {
        Integer id = goodsRepertoryMapper.getIdByGoodsId(goodsId).getId();
        repertoryRegulationMapper.addAvailableRepertory(id,num);
        repertoryRegulationMapper.addTotalRepertory(id,num);
    }

    @Override
    public void cancelDelivery(Integer goodsId,Integer num) {
        Integer id = goodsRepertoryMapper.getIdByGoodsId(goodsId).getId();
        repertoryRegulationMapper.reduceLockedRepertory(id,-num);
        repertoryRegulationMapper.addAvailableRepertory(id,num);
    }

    @Override
    public void replenishRepertory(Integer id, Integer num) {
        repertoryRegulationMapper.addAvailableRepertory(id,num);
        repertoryRegulationMapper.addTotalRepertory(id,num);
    }
}
