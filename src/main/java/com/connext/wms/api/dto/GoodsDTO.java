package com.connext.wms.api.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/10 14:55
 */
@Data
public class GoodsDTO {
    private String sku;
    private String goodsName;
    private BigDecimal goodsPrice;

    public GoodsDTO(String sku, String goodsName, BigDecimal goodsPrice) {
        this.sku = sku;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
    }

    public GoodsDTO() {
    }
}
