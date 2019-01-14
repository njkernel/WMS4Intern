package com.connext.wms.dao;

import com.connext.wms.entity.OutRepertoryDetail;
import com.connext.wms.entity.OutRepertoryDetailExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface OutRepertoryDetailMapper {
    long countByExample(OutRepertoryDetailExample example);

    int deleteByExample(OutRepertoryDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OutRepertoryDetail record);

    int insertSelective(OutRepertoryDetail record);

    List<OutRepertoryDetail> selectByExample(OutRepertoryDetailExample example);

    OutRepertoryDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OutRepertoryDetail record, @Param("example") OutRepertoryDetailExample example);

    int updateByExample(@Param("record") OutRepertoryDetail record, @Param("example") OutRepertoryDetailExample example);

    int updateByPrimaryKeySelective(OutRepertoryDetail record);

    int updateByPrimaryKey(OutRepertoryDetail record);

    //批量插入入库单详情
    int insertDetailList(List<OutRepertoryDetail> outRepertoryDetailList);
}