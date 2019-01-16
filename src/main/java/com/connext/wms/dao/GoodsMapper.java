package com.connext.wms.dao;

import com.connext.wms.api.dto.GoodsDTO;
import com.connext.wms.entity.Goods;
import com.connext.wms.entity.GoodsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GoodsMapper {
    long countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    /**
     * 分页查询所有商品
     * @param start
     * @param size
     * @return
     */
    List<Goods> selectByPage(Integer start,Integer size);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    GoodsDTO selectGoodsDTOBySku(String sku);
}