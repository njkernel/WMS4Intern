package com.connext.wms.service.impl;

import com.connext.wms.dao.OutRepertoryMapper;
import com.connext.wms.entity.*;
import com.connext.wms.service.ExceptionService;
import com.connext.wms.service.OutRepertoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private OutRepertoryService outRepertoryService;

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

    //调用出库模块中的发货方法，对异常的出库单进行再处理
    @Override
    public void deliver(Integer outRepoId){
        //调用出库service中的反馈发货信息方法，对在异常列表的订单进行再次信息反馈
        List<Integer> list = new ArrayList<>();
        list.add(outRepoId);
        OutRepertory outRepertory = new OutRepertory();
        outRepertory.setOutRepoStatus("haveShipped");
        outRepertoryService.updateOutRepoOrderStatus(outRepertory,list);
    }


}
