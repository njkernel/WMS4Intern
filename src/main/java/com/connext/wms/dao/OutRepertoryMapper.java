package com.connext.wms.dao;

import com.connext.wms.entity.OutRepertory;
import com.connext.wms.entity.OutRepertoryExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OutRepertoryMapper {
    long countByExample(OutRepertoryExample example);

    int deleteByExample(OutRepertoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OutRepertory record);

    int insertSelective(OutRepertory record);

    //关键字查询相关异常的订单
    List<OutRepertory> selectByExample(OutRepertoryExample example);

    //分页查询所有异常订单信息
    List<OutRepertory> selectByPage(Integer start,Integer size);

    //通过订单条件查看订单详情
    OutRepertory selectByExample(String condition);


    OutRepertory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OutRepertory record, @Param("example") OutRepertoryExample example);

    int updateByExample(@Param("record") OutRepertory record, @Param("example") OutRepertoryExample example);

    int updateByPrimaryKeySelective(OutRepertory record);

    int updateByPrimaryKey(OutRepertory record);

    OutRepertory selectByOutRepoOrderNo(String outRepoOrderNo);

    List<OutRepertory> selectOutRepoByPage(Integer start,Integer size);
}