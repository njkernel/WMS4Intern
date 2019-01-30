package com.connext.wms.service.impl;

import com.connext.wms.dao.ExpressCompanyMapper;
import com.connext.wms.entity.ExpressCompany;
import com.connext.wms.entity.ExpressCompanyExample;
import com.connext.wms.service.ExpressCompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    //判断的状态
    private Integer flag;

    //分页时每页的数据记录
    public static final Integer SIZE = 10;

    //分页查询所有快递公司信息
    @Override
    public PageInfo selectByPage(Integer currPage){
        PageHelper.startPage(currPage,SIZE);
        List<ExpressCompany> list = expressCompanyMapper.selectByPage();
        return new PageInfo(list);
    }

    //根据关键字查询
    @Override
    public PageInfo selectByKey(Integer currPage, String key){
        String newKey = "%" + key + "%";
        PageHelper.startPage(currPage,SIZE);
        ExpressCompanyExample example = new ExpressCompanyExample();
        example.or().andExpressCompanyNameLike(newKey);
        List<ExpressCompany> list = expressCompanyMapper.selectByExample(example);
        return new PageInfo(list);
    }

    //添加快递公司信息
    @Override
    public Integer insert(String expressCompanyName,String contactWay){
        //1:该公司已存在；2：该公司不存在，可以添加；
            //按公司名查找是否存在记录
        ExpressCompanyExample example = new ExpressCompanyExample();
        example.or().andExpressCompanyNameEqualTo(expressCompanyName);
        List<ExpressCompany> list = expressCompanyMapper.selectByExample(example);
        if (list.size()!= 0) {
            flag = 1;
        } else {
            ExpressCompany expressCompany = new ExpressCompany();
            expressCompany.setExpressCompanyName(expressCompanyName);
            expressCompany.setContactWay(contactWay);
            expressCompanyMapper.insert(expressCompany);
            flag = 2;
        }
        return flag;
    }

    //删除快递公司信息
    @Override
    public void deleteByExample(String expressCompanyName){
        ExpressCompanyExample example = new ExpressCompanyExample();
        example.createCriteria().andExpressCompanyNameEqualTo(expressCompanyName);
        expressCompanyMapper.deleteByExample(example);
    }

    //提供给出库模块使用的查询所有快递公司的方法
    @Override
    public List<ExpressCompany> findAll(){
        ExpressCompanyExample example = new ExpressCompanyExample();
        return expressCompanyMapper.selectByExample(example);
    }

    //修改快递公司信息
    @Override
    public Integer updateByExample(Integer id,String expressCompanyName,String contactWay){
         //1:该公司不存在，可以添加；2：该公司已存在，不能添加
            //通过新公司名称查找是否已经存在该条记录
            ExpressCompanyExample example = new ExpressCompanyExample();
            example.or().andExpressCompanyNameEqualTo(expressCompanyName);
            List<ExpressCompany> list = expressCompanyMapper.selectByExample(example);
            if(list.size()==0){
                ExpressCompany expressCompany = new ExpressCompany();
                expressCompany.setId(id);
                expressCompany.setExpressCompanyName(expressCompanyName);
                expressCompany.setContactWay(contactWay);
                expressCompanyMapper.updateByPrimaryKey(expressCompany);
                flag = 1;
                } else{
                flag = 2;
            }
        return flag;
    }

    //通过id查询快递公司信息
    @Override
    public ExpressCompany findById(Integer id){
        return expressCompanyMapper.selectByPrimaryKey(id);
    }



}
