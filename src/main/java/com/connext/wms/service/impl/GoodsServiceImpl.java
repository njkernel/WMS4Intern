package com.connext.wms.service.impl;

import com.connext.wms.api.dto.GoodsDTO;
import com.connext.wms.dao.GoodsMapper;
import com.connext.wms.entity.Goods;
import com.connext.wms.entity.GoodsExample;
import com.connext.wms.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/8 10:47
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Goods getGoodsBySku(String sku) {
        GoodsExample example = new GoodsExample();
        example.createCriteria().andSkuEqualTo(sku);
        return goodsMapper.selectByExample(example).get(0);
    }

    @Override
    public Goods getGoodsById(Integer id) {
        GoodsExample example = new GoodsExample();
        example.createCriteria().andIdEqualTo(id);
        return goodsMapper.selectByExample(example).get(0);
    }

    @Override
    public List<Goods> getGoodsBySkuList(List<String> skuList) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andSkuIn(skuList);
        return goodsMapper.selectByExample(goodsExample);
    }

    @Override
    public void updateGoodsNameAndPrice(Goods goods) {
        /*
        根据商品id更改商品名称和价格
         */
        goodsMapper.updateByPrimaryKey(goods);
        /*
        调用同步接口传给OMS
         */
        List<GoodsDTO> goodsDTOSList = new ArrayList<>();
        goodsDTOSList.add(goodsMapper.selectGoodsDTOBySku(goods.getSku()));
        restTemplate.postForObject("http://10.129.100.107:8502/updateGoods", goodsDTOSList, String.class);
    }
}
