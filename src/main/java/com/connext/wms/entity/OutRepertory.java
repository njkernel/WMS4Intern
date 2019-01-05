package com.connext.wms.entity;

import java.util.Date;

public class OutRepertory {
    private Integer id;

    private String outRepoId;

    private String orderId;

    private String channelId;

    private String receiverName;

    private String receiverAddress;

    private String expressId;

    private String expressCompany;

    private String outRepoStatus;

    private String syncStatus;

    private Date createTime;

    private String reviser;

    private Date reviseTime;

    public OutRepertory(Integer id, String outRepoId, String orderId, String channelId, String receiverName, String receiverAddress, String expressId, String expressCompany, String outRepoStatus, String syncStatus, Date createTime, String reviser, Date reviseTime) {
        this.id = id;
        this.outRepoId = outRepoId;
        this.orderId = orderId;
        this.channelId = channelId;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.expressId = expressId;
        this.expressCompany = expressCompany;
        this.outRepoStatus = outRepoStatus;
        this.syncStatus = syncStatus;
        this.createTime = createTime;
        this.reviser = reviser;
        this.reviseTime = reviseTime;
    }

    public OutRepertory() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutRepoId() {
        return outRepoId;
    }

    public void setOutRepoId(String outRepoId) {
        this.outRepoId = outRepoId == null ? null : outRepoId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getExpressId() {
        return expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId == null ? null : expressId.trim();
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany == null ? null : expressCompany.trim();
    }

    public String getOutRepoStatus() {
        return outRepoStatus;
    }

    public void setOutRepoStatus(String outRepoStatus) {
        this.outRepoStatus = outRepoStatus == null ? null : outRepoStatus.trim();
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus == null ? null : syncStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReviser() {
        return reviser;
    }

    public void setReviser(String reviser) {
        this.reviser = reviser == null ? null : reviser.trim();
    }

    public Date getReviseTime() {
        return reviseTime;
    }

    public void setReviseTime(Date reviseTime) {
        this.reviseTime = reviseTime;
    }
}