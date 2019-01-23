package com.connext.wms.dao;

import com.connext.wms.api.dto.CodeTotalStockDTO;
import com.connext.wms.entity.GoodsRepertory;
import com.connext.wms.entity.RealRepertoryVO;
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

    /**
     * 返回商品库存表中商品库存的总数
     */
    Integer getCount();

    /**
     * 返回商品库存表中含有关键字名称商品库存的总数
     */
    Integer getCountByKey(String key);

    /**
     * 根据商品名称的关键字查看商品库存表
     * @param start
     * @param size
     * @return
     */
    List<GoodsRepertory> getGoodsRepertoryByGoodsName(Integer start,Integer size,String key);

    /**
     * 根据商品id获取商品库存id
     * @param goodsId
     * @return 商品库存id
     */
    GoodsRepertory getIdByGoodsId(Integer goodsId);

    /**
     * 查看实时库存
     * @return
     */
    List<RealRepertoryVO> getRealVO(String key);
}
