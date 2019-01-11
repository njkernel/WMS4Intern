package com.connext.wms.service.impl;

import com.connext.wms.dao.UserMapper;
import com.connext.wms.entity.User;
import com.connext.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        // TODO Auto-generated method stub
        String password= new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        user.setUserName(user.getUserName());
        user.setTelephone(user.getTelephone());
        userMapper.insert(user);
    }

    @Override
    public int checkRegister(String telephone) {
        return userMapper.checkRegister(telephone);
    }

    @Override
    public User login(Map<String, String> map) {
        // TODO Auto-generated method stub
        return userMapper.login(map);
    }

    @Override
    public void delete(Integer id) {
        this.userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKey(User user) {
        this.userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<User> queryAll() {
        return this.userMapper.queryAll();
    }

    @Override
    public void modifyRole(User user) {
        this.userMapper.modifyRole(user);
    }

    @Override
    public void forget(User user) {
        this.userMapper.forget(user);
    }
}
