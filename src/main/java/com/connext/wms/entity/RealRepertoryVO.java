package com.connext.wms.entity;

import lombok.Data;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/11 10:27
 */
@Data
public class RealRepertoryVO {
    private String sku;
    private String goodsName;
    private Integer realTotalRepertory;
    private Integer realAvailableRepertory;
    private Integer realLockedRepertory;

    public RealRepertoryVO(String sku, String goodsName, Integer realTotalRepertory, Integer realAvailableRepertory, Integer realLockedRepertory) {
        this.sku = sku;
        this.goodsName = goodsName;
        this.realTotalRepertory = realTotalRepertory;
        this.realAvailableRepertory = realAvailableRepertory;
        this.realLockedRepertory = realLockedRepertory;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getRealTotalRepertory() {
        return realTotalRepertory;
    }

    public void setRealTotalRepertory(Integer realTotalRepertory) {
        this.realTotalRepertory = realTotalRepertory;
    }

    public Integer getRealAvailableRepertory() {
        return realAvailableRepertory;
    }

    public void setRealAvailableRepertory(Integer realAvailableRepertory) {
        this.realAvailableRepertory = realAvailableRepertory;
    }

    public Integer getRealLockedRepertory() {
        return realLockedRepertory;
    }

    public void setRealLockedRepertory(Integer realLockedRepertory) {
        this.realLockedRepertory = realLockedRepertory;
    }

    public RealRepertoryVO() {
    }
}
