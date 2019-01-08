package com.connext.wms.service;

import com.connext.wms.entity.Goods;
import org.springframework.stereotype.Service;

/**
 * @Author: Marcus
 * @Date: 2019/1/8 11:04
 * @Version 1.0
 */
public interface GoodsService {
    Goods getGoodsBySku(String sku);
}
