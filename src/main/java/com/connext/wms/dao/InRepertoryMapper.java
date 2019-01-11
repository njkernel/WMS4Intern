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

<<<<<<< HEAD
  /**
   * select all by page
   * @param start start
   * @param size page size
   * @return a page of InRepertory
   */
  List<InRepertory> getAllPage(int start,int size);

  /**
   * select all by page
   * @param start start
   * @param size page size
   * @return a page of InRepertory
   */
  List<InRepertory> getPage(int start,int size);
=======
    List<InRepertory> selectByExample(InRepertoryExample example);
>>>>>>> parent of 3575d3e... 1.0 DAO、SERVICE层基本完成

    InRepertory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InRepertory record, @Param("example") InRepertoryExample example);

    int updateByExample(@Param("record") InRepertory record, @Param("example") InRepertoryExample example);

    int updateByPrimaryKeySelective(InRepertory record);

    int updateByPrimaryKey(InRepertory record);
}