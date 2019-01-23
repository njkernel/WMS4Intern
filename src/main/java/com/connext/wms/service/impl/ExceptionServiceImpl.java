package com.connext.wms.service.impl;

import com.connext.wms.dao.OutRepertoryMapper;
import com.connext.wms.entity.*;
import com.connext.wms.service.ExceptionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Chao.Sun
 * @Date: 2019/1/7  17:00
 * @Version 1.0
 */
@Service
public class ExceptionServiceImpl implements ExceptionService {
    @Autowired
    private OutRepertoryMapper outRepertoryMapper;

    //分页时每页的数据记录
    public static final Integer SIZE = 10;


    //分页查询所有异常订单信息返回列表
    @Override
    public PageInfo selectByPage(Integer currPage){
        PageHelper.startPage(currPage,SIZE);
        List<OutRepertory> list = outRepertoryMapper.selectByPage();
        return new PageInfo(list);
    }

    //查找关键字查找异常订单
    @Override
    public PageInfo selectByExampleToKey(Integer currPage,String key){
        PageHelper.startPage(currPage,SIZE);
        String newKey = "%" + key + "%";
        List<OutRepertory> list = outRepertoryMapper.selectByKey1(newKey);
        return new PageInfo(list);
    }

    //点击查看异常订单详情
    @Override
    public OutRepertory selectByPrimaryKey(Integer id){
        return outRepertoryMapper.selectByPrimaryKey(id);
    }


}
