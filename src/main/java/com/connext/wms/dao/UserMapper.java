package com.connext.wms.dao;

import com.connext.wms.entity.User;
import com.connext.wms.entity.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    //删除用户
    void deleteByPrimaryKey(Integer id);
    //注册
    void insert(User user);
    //登陆
    User login(Map<String, String> map);
    //用户列表
    List<User> queryAll();
    //修改用户信息
    void updateByPrimaryKey(User record);
    User updateById(int id);
    //修改密码
    void forget(User user);
    //重复注册
    int checkRegister(@Param("telephone") String telephone);

    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    User selectUserByPhone(String phone);

}