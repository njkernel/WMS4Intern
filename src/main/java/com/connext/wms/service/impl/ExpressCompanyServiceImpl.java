package com.connext.wms.service.impl;

import com.connext.wms.dao.ExpressCompanyMapper;
import com.connext.wms.entity.ExpressCompany;
import com.connext.wms.entity.ExpressCompanyExample;
import com.connext.wms.service.ExpressCompanyService;
import com.connext.wms.util.Page;
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
    public Page selectByPage(Integer currPage){
        List<ExpressCompany> list = expressCompanyMapper.selectByPage((currPage-1)*Page.PAGE_SIZE, Page.PAGE_SIZE);
        ExpressCompanyExample example = new ExpressCompanyExample();
        Page page = new Page();
        page.setTotalCount((long)expressCompanyMapper.countByExample(example));
        page.setCurrPage(currPage);
        page.init();
        page.setData(list);
        return page;
    }

    //根据关键字查询
    public Page selectByKey(Integer currPage,String key){
        String newKey = "%" + key + "%";
        List<ExpressCompany> list = expressCompanyMapper.selectByKey((currPage-1)*Page.PAGE_SIZE, Page.PAGE_SIZE,newKey);
        ExpressCompanyExample example = new ExpressCompanyExample();
        example.or().andExpressCompanyNameLike(newKey);
        Page page = new Page();
        page.setTotalCount((long)expressCompanyMapper.countByExample(example));
        page.setCurrPage(currPage);
        page.init();
        page.setData(list);
        return page;

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
