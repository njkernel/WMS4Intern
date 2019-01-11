package com.connext.wms.service.impl;

import com.connext.wms.dao.OutRepertoryMapper;
import com.connext.wms.entity.*;
import com.connext.wms.service.ExceptionService;
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
    public List<OutRepertory> selectByPage(Integer start,Integer size){
        Integer pageStart = (start-1)* size;
        return outRepertoryMapper.selectByPage(pageStart,size);
    }

    //查找关键字查找异常订单
    public List<OutRepertory> selectByExampleToKey(String key){
        String newKey = "%" + key + "%";
        OutRepertoryExample example = new OutRepertoryExample();
        example.or().andOutRepoStatusLike(newKey);
        example.or().andExpressCompanyLike(newKey);
        example.or().andOutRepoIdLike(newKey);
        return outRepertoryMapper.selectByExample(example);
    }

    //点击查看异常订单详情
    public OutRepertory selectByExample(String condition){
        OutRepertoryExample example = new OutRepertoryExample();
        example.createCriteria().andOutRepoStatusLike(condition);
        return outRepertoryMapper.selectByExample(condition);
    }

}
