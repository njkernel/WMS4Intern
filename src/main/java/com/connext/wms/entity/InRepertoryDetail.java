package com.connext.wms.entity;

import lombok.ToString;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: Marcus
 * @Date: 2019/1/7 10:02
 * @Version 1.0
 */
@ToString
public class InRepertoryDetail {
    private Integer id;

    private Integer inRepoId;

    private Integer goodsId;

    private String goodsName;

    private Integer goodsNum;

    public InRepertoryDetail(Integer id, Integer inRepoId, Integer goodsId, String goodsName, Integer goodsNum) {
        this.id = id;
        this.inRepoId = inRepoId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsNum = goodsNum;
    }

    public InRepertoryDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInRepoId() {
        return inRepoId;
    }

    public void setInRepoId(Integer inRepoId) {
        this.inRepoId = inRepoId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }
}