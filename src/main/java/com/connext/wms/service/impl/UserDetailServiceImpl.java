package com.connext.wms.service.impl;


import com.connext.wms.entity.User;
import com.connext.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        if (user == null)
            throw new UsernameNotFoundException("账户未注册！");
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        getRoles(user, list);
        org.springframework.security.core.userdetails.User auth_user = new org.springframework.security.core.userdetails.User(user.getTelephone(), user.getPassword(), list);
        return auth_user;

    }

    public void getRoles(User user, List<GrantedAuthority> list) {
        for (String role : user.getRole().split(",")) {
            list.add(new SimpleGrantedAuthority(role));
        }
    }
}
