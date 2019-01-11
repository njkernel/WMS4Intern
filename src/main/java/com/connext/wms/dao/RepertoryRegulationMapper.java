package com.connext.wms.dao;

import com.connext.wms.entity.RepertoryRegulation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/7 11:39
 */
@Mapper
@Repository
public interface RepertoryRegulationMapper {
    /**
     * 对库存增减表进行插入操作
     * @param id
     * @param num
     */
    void addAvailableRepertory(@Param("goodsRepertoryId") Integer id,@Param("availableNumRegulation") Integer num);
    void reduceAvailableRepertory(@Param("goodsRepertoryId") Integer id, @Param("availableNumRegulation") Integer num);
    void addTotalRepertory(@Param("goodsRepertoryId") Integer id,@Param("totalNumRegulation") Integer num);
    void reduceTotalRepertory(@Param("goodsRepertoryId") Integer id,@Param("totalNumRegulation") Integer num);
    void addLockedRepertory(@Param("goodsRepertoryId") Integer id,@Param("lockedNumRegulation") Integer num);
    void reduceLockedRepertory(@Param("goodsRepertoryId") Integer id,@Param("lockedNumRegulation") Integer num);

    /**
     * 对库存增减表summary
     * @return
     */
    List<RepertoryRegulation> summaryRepertory();

    /**
     * 清空库存增减表
     */
    void emptyRepertoryRegulation();

    /**
     * 根据商品库存id对库存增减表summary
     * @return
     */
    RepertoryRegulation summaryRepertoryByRepertoryId(Integer id);

}
