package com.connext.wms.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * @Author: Marcus
 * @Date: 2018/12/28 13:31
 * @Version 1.0
 */
@Component
@Data
public class LoginUser {

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (org.springframework.security.core.userdetails.User) auth.getPrincipal();
    }
}