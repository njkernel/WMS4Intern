package com.connext.wms.entity;

public class TokenManage {
    private Integer id;

    private String omsname;

    private String password;

    public TokenManage(Integer id, String omsname, String password) {
        this.id = id;
        this.omsname = omsname;
        this.password = password;
    }

    public TokenManage() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOmsname() {
        return omsname;
    }

    public void setOmsname(String omsname) {
        this.omsname = omsname == null ? null : omsname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}