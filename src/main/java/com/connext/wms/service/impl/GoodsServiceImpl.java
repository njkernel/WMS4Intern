package com.connext.wms.service.impl;

import com.connext.wms.api.dto.GoodsDTO;
import com.connext.wms.dao.GoodsMapper;
import com.connext.wms.entity.Goods;
import com.connext.wms.entity.GoodsExample;
import com.connext.wms.service.GoodsService;
import com.connext.wms.util.Page;
import com.connext.wms.util.PageSet;
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

    /**
     * 分页查询所有商品并返回到列表
     */
    @Override
    public Page findAll(Integer currPage) {
        List<Goods> list = goodsMapper.selectByPage((currPage - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
        GoodsExample example = new GoodsExample();
        return PageSet.setPage(list, currPage, (long) goodsMapper.countByExample(example));
    }

    /**
     * 根据sku获取商品
     *
     * @param sku
     * @return 商品
     */
    @Override
    public Goods getGoodsBySku(String sku) {
        GoodsExample example = new GoodsExample();
        example.createCriteria().andSkuEqualTo(sku);
        return goodsMapper.selectByExample(example).get(0);
    }

    /**
     * 根据商品id获取商品
     *
     * @param id
     * @return Goods
     */
    @Override
    public Goods getGoodsById(Integer id) {
        GoodsExample example = new GoodsExample();
        example.createCriteria().andIdEqualTo(id);
        return goodsMapper.selectByExample(example).get(0);
    }

    /**
     * 根据skulist返回商品list
     *
     * @param skuList
     * @return List<Goods>
     */
    @Override
    public List<Goods> getGoodsBySkuList(List<String> skuList) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andSkuIn(skuList);
        return goodsMapper.selectByExample(goodsExample);
    }

    /**
     * 更新商品名称和价格并且调用同步接口传给OMS
     *
     * @param goods
     */
    @Override
    public String updateGoodsNameAndPrice(Goods goods) {
        /*
        根据商品id更改商品名称和价格
         */
        if (goods.getGoodsPrice() <= 0) {
            return "error";
        } else {
            goodsMapper.updateByPrimaryKeySelective(goods);
            /*
        调用同步接口传给OMS
         */
            List<GoodsDTO> goodsDTOSList = new ArrayList<>();
            String sku = goodsMapper.selectByPrimaryKey(goods.getId()).getSku();
            goodsDTOSList.add(goodsMapper.selectGoodsDTOBySku(sku));
            // System.out.println(goodsDTOSList.toString());
            restTemplate.postForObject("http://10.129.100.78:8502/updateGoods", goodsDTOSList, String.class);
            return "success";
        }

    }

    /**
     * 根据关键字查询相关的商品信息
     */
    @Override
    public Page selectByExample(String key) {
        String newKey = "%" + key + "%";
        Integer currPage = 1;
        GoodsExample example = new GoodsExample();
        example.or().andGoodsNameLike(newKey);
        List<Goods> list = goodsMapper.selectByExample(example);
        return PageSet.setPage(list, currPage, (long) goodsMapper.countByExample(example));
    }
}
