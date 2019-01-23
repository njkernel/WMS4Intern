package com.connext.wms.service;


import com.connext.wms.entity.RealRepertoryVO;
import com.connext.wms.util.Page;

import java.util.List;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/8 16:39
 */
public interface GoodsRepertoryService {
    /**
     * 更新商品库存表，更新完之后将原库存增减表进行清空
     * 并且调用同步接口将数据传输给OMS
     */
    void updateGoodsRepertory();

    /**
     * 显示实时库存
     *
     * @return 库存视图对象
     */
    Page showRealRepertory(Integer currPage, String key);

    /**
     * 根据商品名称的关键字查询实时库存
     *
     * @param key      商品名称关键字
     * @param currPage 当前页
     * @return 库存视图对象
     */
    Page getGoodsRepertoryByGoodsName(Integer currPage, String key);
}
