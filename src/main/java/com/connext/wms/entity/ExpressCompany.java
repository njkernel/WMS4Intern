package com.connext.wms.entity;

import lombok.ToString;

@ToString
public class ExpressCompany {
    //快递公司id
    private Integer id;

    //快递公司名称
    private String expressCompanyName;

    //快递公司联系方式
    private String contactWay;

    public ExpressCompany(Integer id, String expressCompanyName, String contactWay) {
        this.id = id;
        this.expressCompanyName = expressCompanyName;
        this.contactWay = contactWay;
    }

    public ExpressCompany() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpressCompanyName() {
        return expressCompanyName;
    }

    public void setExpressCompanyName(String expressCompanyName) {
        this.expressCompanyName = expressCompanyName == null ? null : expressCompanyName.trim();
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay == null ? null : contactWay.trim();
    }

    @Override
    public String toString() {
        return "ExpressCompany{" +
                "id=" + id +
                ", expressCompanyName='" + expressCompanyName + '\'' +
                ", contactWay='" + contactWay + '\'' +
                '}';
    }
}