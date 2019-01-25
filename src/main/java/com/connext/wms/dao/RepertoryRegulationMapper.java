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
    /**
     * 增加可用库存
     *
     * @param id
     * @param num
     */
    void addAvailableRepertory(@Param("goodsRepertoryId") Integer id, @Param("availableNumRegulation") Integer num);

    /**
     * 减少可用库存
     *
     * @param id
     * @param num
     */
    void reduceAvailableRepertory(@Param("goodsRepertoryId") Integer id, @Param("availableNumRegulation") Integer num);

    /**
     * 增加总库存
     *
     * @param id
     * @param num
     */
    void addTotalRepertory(@Param("goodsRepertoryId") Integer id, @Param("totalNumRegulation") Integer num);

    /**
     * 减少总库存
     *
     * @param id
     * @param num
     */
    void reduceTotalRepertory(@Param("goodsRepertoryId") Integer id, @Param("totalNumRegulation") Integer num);

    /**
     * 增加锁定库存
     *
     * @param id
     * @param num
     */
    void addLockedRepertory(@Param("goodsRepertoryId") Integer id, @Param("lockedNumRegulation") Integer num);

    /**
     * 减少锁定库存
     *
     * @param id
     * @param num
     */
    void reduceLockedRepertory(@Param("goodsRepertoryId") Integer id, @Param("lockedNumRegulation") Integer num);

    /**
     * 对库存增减表summary
     *
     * @return
     */
    List<RepertoryRegulation> summaryRepertory();

    /**
     * 清空库存增减表
     */
    void emptyRepertoryRegulation();

    /**
     * 根据商品库存id对库存增减表summary
     *
     * @return
     */
    RepertoryRegulation summaryRepertoryByRepertoryId(Integer id);

    /**
     * 根据商品库存id对库存增减表的商品进行单个清空
     */
    void deleteRepertoryByRepertoryId(Integer id);

}
