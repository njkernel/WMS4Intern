package com.connext.wms.entity;
/**
 *
 */

import lombok.Data;
import lombok.ToString;

@ToString
public class Goods {
    private Integer id;

    private String sku;

    private String goodsName;

    private Float goodsPrice;

    public Goods(Integer id, String sku, String goodsName, Float goodsPrice) {
        this.id = id;
        this.sku = sku;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
    }

    public Goods() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}