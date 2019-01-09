package com.connext.wms.service.impl;

import com.connext.wms.dao.GoodsMapper;
import com.connext.wms.entity.Goods;
import com.connext.wms.entity.GoodsExample;
import com.connext.wms.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Marcus
 * @Date: 2019/1/8 13:04
 * @Version 1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public Goods getGoodsBySku(String sku) {
        GoodsExample example=new GoodsExample();
        example.createCriteria().andSkuEqualTo(sku);
        return goodsMapper.selectByExample(example).get(0);
    }
}
