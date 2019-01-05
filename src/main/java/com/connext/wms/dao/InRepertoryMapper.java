package com.connext.wms.dao;

import com.connext.wms.entity.InRepertory;
import com.connext.wms.entity.InRepertoryExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InRepertoryMapper {
    long countByExample(InRepertoryExample example);

    int deleteByExample(InRepertoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InRepertory record);

    int insertSelective(InRepertory record);

    List<InRepertory> selectByExample(InRepertoryExample example);

    InRepertory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InRepertory record, @Param("example") InRepertoryExample example);

    int updateByExample(@Param("record") InRepertory record, @Param("example") InRepertoryExample example);

    int updateByPrimaryKeySelective(InRepertory record);

    int updateByPrimaryKey(InRepertory record);
}