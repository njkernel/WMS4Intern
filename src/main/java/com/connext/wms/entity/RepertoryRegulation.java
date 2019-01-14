package com.connext.wms.entity;

import lombok.ToString;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/7 14:49
 */

@ToString
public class RepertoryRegulation {

    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private Integer goodsRepertoryId;

    /**
     *
     */
    private Integer availableNumRegulation;

    private Integer totalNumRegulation;

    private Integer lockedNumRegulation;

    private Integer availableResult;

    private Integer lockedResult;

    private Integer totalResult;

    public RepertoryRegulation() {
    }

    public RepertoryRegulation(Integer id, Integer goodsRepertoryId, Integer availableNumRegulation, Integer totalNumRegulation, Integer lockedNumRegulation, Integer availableResult, Integer lockedResult, Integer totalResult) {
        this.id = id;
        this.goodsRepertoryId = goodsRepertoryId;
        this.availableNumRegulation = availableNumRegulation;
        this.totalNumRegulation = totalNumRegulation;
        this.lockedNumRegulation = lockedNumRegulation;
        this.availableResult = availableResult;
        this.lockedResult = lockedResult;
        this.totalResult = totalResult;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsRepertoryId() {
        return goodsRepertoryId;
    }

    public void setGoodsRepertoryId(Integer goodsRepertoryId) {
        this.goodsRepertoryId = goodsRepertoryId;
    }

    public Integer getAvailableResult() {
        return availableResult;
    }

    public void setAvailableResult(Integer availableResult) {
        this.availableResult = availableResult;
    }

    public Integer getLockedResult() {
        return lockedResult;
    }

    public void setLockedResult(Integer lockedResult) {
        this.lockedResult = lockedResult;
    }

    public Integer getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(Integer totalResult) {
        this.totalResult = totalResult;
    }

    public Integer getAvailableNumRegulation() {
        return availableNumRegulation;
    }

    public void setAvailableNumRegulation(Integer availableNumRegulation) {
        this.availableNumRegulation = availableNumRegulation;
    }

    public Integer getTotalNumRegulation() {
        return totalNumRegulation;
    }

    public void setTotalNumRegulation(Integer totalNumRegulation) {
        this.totalNumRegulation = totalNumRegulation;
    }

    public Integer getLockedNumRegulation() {
        return lockedNumRegulation;
    }

    public void setLockedNumRegulation(Integer lockedNumRegulation) {
        this.lockedNumRegulation = lockedNumRegulation;
    }
}
