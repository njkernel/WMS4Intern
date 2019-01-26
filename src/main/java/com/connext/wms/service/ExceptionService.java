package com.connext.wms.service;

import com.connext.wms.entity.OutRepertory;
import com.github.pagehelper.PageInfo;


/**
 * @Author: Chao.Sun
 * @Date: 2019/1/7 17:00
 * @Version 1.0
 */

public interface ExceptionService {

    //查找关键字查找异常订单
    PageInfo selectByExampleToKey(Integer currPage,String key);

    //点击查看异常订单详情
    OutRepertory selectByPrimaryKey(Integer id);

    //分页查询所有异常订单信息返回列表
    PageInfo selectByPage(Integer currPage);

    //调用出库模块中的发货方法，对异常的出库单进行再处理
    void deliver(Integer outRepoId);

}
