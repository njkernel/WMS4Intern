package com.connext.wms.entity;

import lombok.ToString;

import java.util.Date;
@ToString
public class InRepertory {
    private Integer id;

    private String inRepoId;

    private String orderId;

    private String channelId;

    private String expressId;

    private String expressCompany;

    private String inRepoStatus;

    private String syncStatus;

    private String receivingRepo;

    private Date createTime;

    private String reviser;

    private Date reviseTime;

<<<<<<< HEAD
    private List<InRepertoryDetail> repertoryDetails;
    public InRepertory(String inRepoId, String orderId, String channelId, String expressId, String expressCompany, String inRepoStatus, String syncStatus, String receivingRepo, Date createTime, String reviser, Date reviseTime) {
        this.inRepoId = inRepoId;
        this.orderId = orderId;
        this.channelId = channelId;
        this.expressId = expressId;
        this.expressCompany = expressCompany;
        this.inRepoStatus = inRepoStatus;
        this.syncStatus = syncStatus;
        this.receivingRepo = receivingRepo;
        this.createTime = createTime;
        this.reviser = reviser;
        this.reviseTime = reviseTime;
    }
<<<<<<< HEAD

=======
>>>>>>> parent of 3575d3e... 1.0 DAO、SERVICE层基本完成
=======
>>>>>>> parent of 7aa2b13... Merge pull request #2 from njkernel/Marcus
    public InRepertory(Integer id, String inRepoId, String orderId, String channelId, String expressId, String expressCompany, String inRepoStatus, String syncStatus, String receivingRepo, Date createTime, String reviser, Date reviseTime) {
        this.id = id;
        this.inRepoId = inRepoId;
        this.orderId = orderId;
        this.channelId = channelId;
        this.expressId = expressId;
        this.expressCompany = expressCompany;
        this.inRepoStatus = inRepoStatus;
        this.syncStatus = syncStatus;
        this.receivingRepo = receivingRepo;
        this.createTime = createTime;
        this.reviser = reviser;
        this.reviseTime = reviseTime;
    }

    public InRepertory() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInRepoId() {
        return inRepoId;
    }

    public void setInRepoId(String inRepoId) {
        this.inRepoId = inRepoId == null ? null : inRepoId.trim();
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

    public String getInRepoStatus() {
        return inRepoStatus;
    }

    public void setInRepoStatus(String inRepoStatus) {
        this.inRepoStatus = inRepoStatus == null ? null : inRepoStatus.trim();
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus == null ? null : syncStatus.trim();
    }

    public String getReceivingRepo() {
        return receivingRepo;
    }

    public void setReceivingRepo(String receivingRepo) {
        this.receivingRepo = receivingRepo == null ? null : receivingRepo.trim();
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