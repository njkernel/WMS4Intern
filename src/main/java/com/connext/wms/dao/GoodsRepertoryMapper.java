package com.connext.wms.dao;

import com.connext.wms.api.dto.CodeTotalStockDTO;
import com.connext.wms.entity.GoodsRepertory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/8 11:38
 */
@Mapper
@Repository
public interface GoodsRepertoryMapper {
    /**
     * 更新商品库存的方法
     */
    void updateGoodsRepertory(@Param("tnum") Integer tnum, @Param("anum") Integer anum, @Param("lnum") Integer lnum, @Param("id") Integer id);

    /**
     * 调用同步接口前查询商品对应的sku和库存
     * @return
     */
    List<CodeTotalStockDTO> getCodeTotalStockDTO();

    /**
     * 查看商品库存表的所有信息
     * @return
     */
    List<GoodsRepertory> getGoodsRepertory(Integer start,Integer size);
}
