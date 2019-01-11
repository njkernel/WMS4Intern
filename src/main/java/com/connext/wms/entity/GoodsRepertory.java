package com.connext.wms.entity;

import lombok.ToString;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/8 15:29
 */

@ToString
public class GoodsRepertory {
    private Integer id;
    private Integer goodsId;
    private Integer totalNum;
    private Integer availableNum;
    private Integer lockedNum;

    public GoodsRepertory() {
    }

    public GoodsRepertory(Integer id, Integer goodsId, Integer totalNum, Integer availableNum, Integer lockedNum) {
        this.id = id;
        this.goodsId = goodsId;
        this.totalNum = totalNum;
        this.availableNum = availableNum;
        this.lockedNum = lockedNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(Integer availableNum) {
        this.availableNum = availableNum;
    }

    public Integer getLockedNum() {
        return lockedNum;
    }

    public void setLockedNum(Integer lockedNum) {
        this.lockedNum = lockedNum;
    }


}
