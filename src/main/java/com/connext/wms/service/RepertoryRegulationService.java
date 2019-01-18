package com.connext.wms.service;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/7 14:58
 */
public interface RepertoryRegulationService {
    /**
     * 发货操作——已出库状态
     */
    void deliveryGoodsBeforeDelivery(Integer id, Integer num);

    /**
     * 发货操作——已发货状态
     */
    void deliveryGoodsAfterDelivery(Integer id, Integer num);

    /**
     * 退货操作——收货成功
     */
    void rejectedGoodsSuccess(Integer id, Integer num);

    /**
     * 取消操作——已出库未发货状态
     */
    void cancelDelivery(Integer id, Integer num);

    /**
     * 补货操作
     *
     * @param id
     * @param num
     * @return String
     */
    String replenishRepertory(Integer id, Integer num);

}
