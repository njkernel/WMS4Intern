package com.connext.wms.entity;

import lombok.ToString;

@ToString
public class User {
    private Integer id;

    private String userName;

    private String password;

    private String telephone;

    private  String role;

    public User(Integer id, String userName, String password, String telephone, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.telephone = telephone;
        this.role=role;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}