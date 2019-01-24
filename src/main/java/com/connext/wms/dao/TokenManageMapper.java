package com.connext.wms.dao;

import com.connext.wms.entity.TokenManage;
import com.connext.wms.entity.TokenManageExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TokenManageMapper {
    TokenManage selectByOmsname(String omsname);

    long countByExample(TokenManageExample example);

    int deleteByExample(TokenManageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TokenManage record);

    int insertSelective(TokenManage record);

    List<TokenManage> selectByExample(TokenManageExample example);

    TokenManage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TokenManage record, @Param("example") TokenManageExample example);

    int updateByExample(@Param("record") TokenManage record, @Param("example") TokenManageExample example);

    int updateByPrimaryKeySelective(TokenManage record);

    int updateByPrimaryKey(TokenManage record);
}