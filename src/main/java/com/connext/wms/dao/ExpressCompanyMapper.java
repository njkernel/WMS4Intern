package com.connext.wms.dao;

import com.connext.wms.entity.ExpressCompany;
import com.connext.wms.entity.ExpressCompanyExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ExpressCompanyMapper {
    long countByExample(ExpressCompanyExample example);

    int deleteByExample(ExpressCompanyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExpressCompany record);

    int insertSelective(ExpressCompany record);

    List<ExpressCompany> selectByExample(ExpressCompanyExample example);

    ExpressCompany selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExpressCompany record, @Param("example") ExpressCompanyExample example);

    int updateByExample(@Param("record") ExpressCompany record, @Param("example") ExpressCompanyExample example);

    int updateByPrimaryKeySelective(ExpressCompany record);

    int updateByPrimaryKey(ExpressCompany record);
}