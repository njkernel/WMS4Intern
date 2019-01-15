package com.connext.wms.service;

import com.connext.wms.entity.OutRepertory;

import java.util.List;

/**
 * @Author: Chao.Sun
 * @Date: 2019/1/7 17:00
 * @Version 1.0
 */

public interface ExceptionService {

    //查找关键字查找异常订单
    List<OutRepertory> selectByExampleToKey(String key);

    //点击查看异常订单详情
    OutRepertory selectByPrimaryKey(Integer id);

    //分页查询所有异常订单信息返回列表
    List<OutRepertory> selectByPage(Integer start,Integer size);

}
