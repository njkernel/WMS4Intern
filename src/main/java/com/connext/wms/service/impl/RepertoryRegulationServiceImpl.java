package com.connext.wms.service.impl;

import com.connext.wms.dao.RepertoryRegulationMapper;
import com.connext.wms.service.RepertoryRegulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/7 15:00
 */
@Service
public class RepertoryRegulationServiceImpl implements RepertoryRegulationService {
    @Autowired
    RepertoryRegulationMapper repertoryRegulationMapper;
    @Override
    public void deliveryGoodsBeforeDelivery(Integer id,Integer num) {
        repertoryRegulationMapper.addLockedRepertory(id,num);
        repertoryRegulationMapper.reduceAvailableRepertory(id,-num);
    }

    @Override
    public void deliveryGoodsAfterDelivery(Integer id,Integer num) {
        repertoryRegulationMapper.reduceLockedRepertory(id,-num);
        repertoryRegulationMapper.reduceTotalRepertory(id,-num);
    }

    @Override
    public void rejectedGoodsSuccess(Integer id,Integer num) {
        repertoryRegulationMapper.addAvailableRepertory(id,num);
        repertoryRegulationMapper.addTotalRepertory(id,num);
    }

    @Override
    public void cancelDelivery(Integer id,Integer num) {
        repertoryRegulationMapper.reduceLockedRepertory(id,-num);
        repertoryRegulationMapper.addAvailableRepertory(id,num);
    }

    @Override
    public void replenishRepertory(Integer id, Integer num) {
        repertoryRegulationMapper.addAvailableRepertory(id,num);
        repertoryRegulationMapper.addTotalRepertory(id,num);
    }
}
