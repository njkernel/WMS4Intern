package com.connext.wms.service.impl;

import com.connext.wms.dao.OutRepertoryMapper;
import com.connext.wms.entity.*;
import com.connext.wms.service.ExceptionService;
import com.connext.wms.util.Page;
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

    //分页查询所有异常订单信息返回列表
    public Page selectByPage(Integer currPage){
        List<OutRepertory> list = outRepertoryMapper.selectByPage((currPage-1)*Page.PAGE_SIZE, Page.PAGE_SIZE);
        OutRepertoryExample example = new OutRepertoryExample();
        return byPage(currPage,list,example);
    }

    //查找关键字查找异常订单
    public Page selectByExampleToKey(Integer currPage,String key){
        String newKey = "%" + key + "%";
        List<OutRepertory> list = outRepertoryMapper.selectByKey1(newKey,(currPage-1)*Page.PAGE_SIZE, Page.PAGE_SIZE);
        OutRepertoryExample example = new OutRepertoryExample();
        example.or().andOutRepoIdLike(newKey);
        return byPage(currPage,list,example);
    }

    //点击查看异常订单详情
    public OutRepertory selectByPrimaryKey(Integer id){
        return outRepertoryMapper.selectByPrimaryKey(id);
    }

    //分页实现方法
    Page byPage(Integer currPage,List<OutRepertory> list,OutRepertoryExample example){
        Page page = new Page();
        page.setTotalCount((long)outRepertoryMapper.countByExample(example));
        page.setCurrPage(currPage);
        page.init();
        page.setData(list);
        return page;
    }

}
