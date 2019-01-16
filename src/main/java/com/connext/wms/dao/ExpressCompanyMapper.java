package com.connext.wms.dao;
/**
 * @Author: Chao.Sun
 * @Date: 2019/1/7 9:37
 * @Version 1.0
 */

import com.connext.wms.entity.ExpressCompany;
import com.connext.wms.entity.ExpressCompanyExample;
import java.util.List;

import com.connext.wms.entity.OutRepertoryExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ExpressCompanyMapper {
    //查找所有的数据有多少条
    long countByExample(ExpressCompanyExample  example);

    //根据条件删除
    void deleteByExample(ExpressCompanyExample example);

    //根据主键删除
    int deleteByPrimaryKey(Integer id);

    //添加快递公司
    void insert(ExpressCompany record);

    //选择性的插入数据
    int insertSelective(ExpressCompany record);

    //查询所有快递公司信息
    List<ExpressCompany> selectByExample(ExpressCompanyExample example);

    //分页按照关键字查询公司信息
    List<ExpressCompany> selectByPage(Integer start,Integer size);

    //分页查询所有快递公司信息
    List<ExpressCompany> selectByKey(Integer start,Integer size,String key);

    ExpressCompany selectByPrimaryKey(Integer id);

    //选择性的根据一些条件修改信息
    int updateByExampleSelective(@Param("record") ExpressCompany record, @Param("example") ExpressCompanyExample example);

    //修改信息
    int updateByExample(@Param("record") ExpressCompany record, @Param("example") ExpressCompanyExample example);

    int updateByPrimaryKeySelective(ExpressCompany record);

    int updateByPrimaryKey(ExpressCompany record);
}