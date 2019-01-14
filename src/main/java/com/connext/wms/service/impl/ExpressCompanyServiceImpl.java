package com.connext.wms.service.impl;

import com.connext.wms.dao.ExpressCompanyMapper;
import com.connext.wms.entity.ExpressCompany;
import com.connext.wms.entity.ExpressCompanyExample;
import com.connext.wms.service.ExpressCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Chao.Sun
 * @Date: 2019/1/7 9:37
 * @Version 1.0
 */

@Service
public class ExpressCompanyServiceImpl implements ExpressCompanyService {
    @Autowired
    private ExpressCompanyMapper expressCompanyMapper;


    //分页查询所有快递公司信息
    public List<ExpressCompany> selectByPage(Integer start,Integer size){
        Integer pageStart = (start-1)* size;
        return expressCompanyMapper.selectByPage(pageStart,size);
    }

    //根据关键字查询
    public List<ExpressCompany> selectByExample(String key){
        String newKey = "%" + key + "%";
        ExpressCompanyExample example = new ExpressCompanyExample();
        example.createCriteria().andExpressCompanyNameLike(newKey);
        return expressCompanyMapper.selectByExample(example);
    }

    //添加快递公司信息
    public void insert(String expressCompanyName,String contactWay){
        ExpressCompany expressCompany = new ExpressCompany();
        expressCompany.setExpressCompanyName(expressCompanyName);
        expressCompany.setContactWay(contactWay);
        expressCompanyMapper.insert(expressCompany);
    }

    //删除快递公司信息
    public void deleteByExample(String expressCompanyName){
        ExpressCompanyExample example = new ExpressCompanyExample();
        example.createCriteria().andExpressCompanyNameEqualTo(expressCompanyName);
        expressCompanyMapper.deleteByExample(example);
    }

    //修改快递公司信息
    public void updateByExample(String newName,String expressCompanyName,String contactWay){
        ExpressCompany record = new ExpressCompany();
        record.setExpressCompanyName(newName);
        record.setContactWay(contactWay);
        ExpressCompanyExample example = new ExpressCompanyExample();
        example.createCriteria().andExpressCompanyNameEqualTo(expressCompanyName);
        expressCompanyMapper.updateByExample(record,example);
    }




}
