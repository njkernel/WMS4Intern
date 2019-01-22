package com.connext.wms.service;

import com.connext.wms.entity.ExpressCompany;
import com.connext.wms.util.Page;

import java.util.List;


/**
 * @Author: Chao.Sun
 * @Date: 2019/1/7 9:37
 * @Version 1.0
 */


public interface ExpressCompanyService {

    //分页查询所有快递公司信息
    Page selectByPage(Integer currPage);

    //根据关键字查询
    Page selectByKey(Integer currPage,String key);

    //添加快递公司信息
    Integer insert(String expressCompanyName,String contactWay);

    //根据公司名称删除信息
    void deleteByExample(String expressCompanyName);

    //修改快递公司信息
    Integer updateByExample(String newName,String expressCompanyName,String contactWay);

    //提供给出库模块使用的查询所有快递公司的方法
    List<ExpressCompany> findAll();

}
