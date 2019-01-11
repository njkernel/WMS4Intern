package com.connext.wms.service.impl;


import com.connext.wms.dao.UserRepository;
import com.connext.wms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(userName);
        if (user==null)
            throw new UsernameNotFoundException("账户未注册！");
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        getRoles(user,list);
            org.springframework.security.core.userdetails.User auth_user = new org.springframework.security.core.userdetails.User(user.getTelephone(),user.getPassword(),list);
            return auth_user;

    }

    public void getRoles(User user, List<GrantedAuthority> list){
        for (String role:user.getRole().split(",")){
            list.add(new SimpleGrantedAuthority("ROLE_"+role));
        }
    }
}
