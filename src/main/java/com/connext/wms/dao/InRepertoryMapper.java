package com.connext.wms.dao;

import com.connext.wms.entity.InRepertory;
import com.connext.wms.entity.InRepertoryExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:18
 * @Version 1.0 */
@Mapper
@Repository
public interface InRepertoryMapper {
  /**
   * countByExample
   *
   * @param example InRepertoryExample
   * @return Total number of Example
   */
  long countByExample(InRepertoryExample example);

  /**
   * deleteByExample
   *
   * @param example InRepertoryExample
   * @return if return >0 true
   */
  int deleteByExample(InRepertoryExample example);

  /**
   * deleteByPrimaryKey
   *
   * @param id InRepertoryExample's id
   * @return if return >0 true
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * insert a InRepertory
   *
   * @param record the InRepertory
   * @return affect rows
   */
  int insert(InRepertory record);

  /**
   * insertSelective
   *
   * @param record the InRepertory
   * @return affect rows
   */
  int insertSelective(InRepertory record);

  /**
   * selectByExample
   *
   * @param example InRepertoryExample
   * @return a list of InRepertory
   */
  List<InRepertory> selectByExample(InRepertoryExample example);



  /** selectByPrimaryKey
   * @param id InRepertoryExample's id
   * @return the InRepertory
   */
  InRepertory selectByPrimaryKey(Integer id);

  /**
   * updateByExampleSelective
   * @param record InRepertory
   * @param example InRepertoryExample
   * @return affect rows
   */
  int updateByExampleSelective(
      @Param("record") InRepertory record, @Param("example") InRepertoryExample example);

  /** updateByExample
   * @param record InRepertory
   * @param example InRepertoryExample
   * @return affect rows
   */
  int updateByExample(
      @Param("record") InRepertory record, @Param("example") InRepertoryExample example);

  /**
   * updateByPrimaryKeySelective
   * @param record InRepertory
   * @return affect rows
   */
  int updateByPrimaryKeySelective(InRepertory record);

  /** updateByPrimaryKey
   * @param record InRepertory
   * @return affect rows
   */
  int updateByPrimaryKey(InRepertory record);

  /**
   * find all InRepertory like sth
   * @param status InRepertory status
   * @param like search pattern
   * @return list of InRepertory
   */
  List<InRepertory> findAllLike(String status,String like);
}
