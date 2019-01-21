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

    /**
     * 发货操作——已出库状态
     */
    @Override
    public void deliveryGoodsBeforeDelivery(Integer goodsId, Integer num) {
        Integer id = goodsRepertoryMapper.getIdByGoodsId(goodsId).getId();
        repertoryRegulationMapper.addLockedRepertory(id, num);
        repertoryRegulationMapper.reduceAvailableRepertory(id, -num);
    }

    /**
     * 发货操作——已发货状态
     */
    @Override
    public void deliveryGoodsAfterDelivery(Integer goodsId, Integer num) {
        Integer id = goodsRepertoryMapper.getIdByGoodsId(goodsId).getId();
        repertoryRegulationMapper.reduceLockedRepertory(id, -num);
        repertoryRegulationMapper.reduceTotalRepertory(id, -num);
    }

    /**
     * 退货操作——收货成功
     */
    @Override
    public void rejectedGoodsSuccess(Integer goodsId, Integer num) {
        Integer id = goodsRepertoryMapper.getIdByGoodsId(goodsId).getId();
        repertoryRegulationMapper.addAvailableRepertory(id, num);
        repertoryRegulationMapper.addTotalRepertory(id, num);
    }

    /**
     * 取消操作——已出库未发货状态
     */
    @Override
    public void cancelDelivery(Integer goodsId, Integer num) {
        Integer id = goodsRepertoryMapper.getIdByGoodsId(goodsId).getId();
        repertoryRegulationMapper.reduceLockedRepertory(id, -num);
        repertoryRegulationMapper.addAvailableRepertory(id, num);
    }

    /**
     * 补货
     *
     * @param id
     * @param num
     */
    @Override
    public String replenishRepertory(Integer id, Integer num) {
        if (num <= 0) {
            return "error";
        } else {
            repertoryRegulationMapper.addAvailableRepertory(id, num);
            repertoryRegulationMapper.addTotalRepertory(id, num);
            return "success";
        }

    }
}
