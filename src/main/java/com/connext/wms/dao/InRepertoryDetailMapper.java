package com.connext.wms.dao;

import com.connext.wms.entity.InRepertoryDetail;
import com.connext.wms.entity.InRepertoryDetailExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:18
 * @Version 1.0
 */
@Mapper
@Repository
public interface InRepertoryDetailMapper {
    /**
     * countByExample
     *
     * @param example InRepertoryDetailExample
     * @return result
     */
    long countByExample(InRepertoryDetailExample example);

    /**
     * delete one By Example
     *
     * @param example InRepertoryDetailExample
     * @return effect rows
     */
    int deleteByExample(InRepertoryDetailExample example);

    /**
     * delete one By id
     *
     * @param id InRepertoryDetail id
     * @return effect rows
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * add a InRepertoryDetail
     *
     * @param record InRepertoryDetail
     * @return effect rows
     */
    int insert(InRepertoryDetail record);

    /**
     * insertSelective
     *
     * @param record InRepertoryDetail
     * @return effect rows
     */
    int insertSelective(InRepertoryDetail record);

    /**
     * selectByExample
     *
     * @param example InRepertoryDetailExample
     * @return a list of InRepertoryDetail
     */
    List<InRepertoryDetail> selectByExample(InRepertoryDetailExample example);

    /**
     * selectByPrimaryKey
     *
     * @param id PrimaryKey of InRepertoryDetail
     * @return InRepertoryDetail
     */
    InRepertoryDetail selectByPrimaryKey(Integer id);

    /**
     * update InRepertoryDetail
     *
     * @param record  InRepertoryDetail
     * @param example InRepertoryDetailExample
     * @return effect rows
     */
    int updateByExampleSelective(@Param("record") InRepertoryDetail record, @Param("example") InRepertoryDetailExample example);

    /**
     * update InRepertoryDetail
     *
     * @param record  InRepertoryDetail
     * @param example InRepertoryDetailExample
     * @return effect rows
     */
    int updateByExample(@Param("record") InRepertoryDetail record, @Param("example") InRepertoryDetailExample example);

    /**
     * update InRepertoryDetail
     *
     * @param record InRepertoryDetail
     * @return effect rows
     */
    int updateByPrimaryKeySelective(InRepertoryDetail record);

    /**
     * update InRepertoryDetail
     *
     * @param record InRepertoryDetail
     * @return effect rows
     */
    int updateByPrimaryKey(InRepertoryDetail record);
}