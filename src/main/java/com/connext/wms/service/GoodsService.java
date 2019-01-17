package com.connext.wms.service;

import com.connext.wms.entity.Goods;
import com.connext.wms.util.Page;

import java.util.List;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/8 10:47
 */
public interface GoodsService {

    /**
     * 分页查询所有商品并返回到列表
     */
    Page findAll(Integer currPage);

    /**
     * 根据商品sku返回商品对象
     *
     * @param sku
     * @return 商品对象
     */
    Goods getGoodsBySku(String sku);

    /**
     * 根据商品id返回商品对象
     * @param id
     * @return 商品对象
     */
    Goods getGoodsById(Integer id);

    /**
     * 根据多个sku批量查询多个商品
     * @param skuList
     * @return 商品对象list
     */
    List<Goods> getGoodsBySkuList(List<String> skuList);

    /**
     * 根据商品id改变商品价格和名称
     * 并且调用同步接口对OMS进行更新
     * @param goods
     */
    void updateGoodsNameAndPrice(Goods goods);

    /**
     * 根据关键字查询相关的商品信息
     */
    Page selectByExample(String key);

}
