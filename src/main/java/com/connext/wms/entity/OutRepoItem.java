package com.connext.wms.entity;

import lombok.Data;

/**
 * @author xiamingxing
 * @date 2019/1/19 3:30
 */
@Data
public class OutRepoItem {
    private String goodsSku;
    private String goodsName;
    private float price;
    private Integer num;

    public OutRepoItem(String goodsSku, String goodsName, float price, Integer num) {
        this.goodsSku = goodsSku;
        this.goodsName = goodsName;
        this.price = price;
        this.num = num;
    }
}
