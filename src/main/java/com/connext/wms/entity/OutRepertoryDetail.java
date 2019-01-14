package com.connext.wms.entity;


public class OutRepertoryDetail {
    private Integer id;

    private Integer outRepoId;

    private Integer goodsId;

    private String goodsName;

    private Integer goodsNum;

    public OutRepertoryDetail(Integer id, Integer outRepoId, Integer goodsId, String goodsName, Integer goodsNum) {
        this.id = id;
        this.outRepoId = outRepoId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsNum = goodsNum;
    }

    public OutRepertoryDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOutRepoId() {
        return outRepoId;
    }

    public void setOutRepoId(Integer outRepoId) {
        this.outRepoId = outRepoId;
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

    @Override
    public String toString() {
        return "OutRepertoryDetail{" +
                "id=" + id +
                ", outRepoId=" + outRepoId +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsNum=" + goodsNum +
                '}';
    }
}