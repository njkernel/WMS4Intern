package com.connext.wms.service;

import com.connext.wms.entity.OutRepertory;
import com.connext.wms.entity.OutRepoItem;
import com.connext.wms.util.Page;

import java.util.List;


/**
 * @author xiamingxing
 * @date 2019/1/7 9:31
 */
public interface OutRepertoryService {
    /**
     * 分页查询出库单
     * @param currPage
     * @return Page
     */
    Page outRepoOrderList(Integer currPage);

    /**
     * 模糊查询
     * @param outRepoOrderId
     * @param selectStatus
     * @param currPage
     * @return Page
     */
    Page unclearSelect(String outRepoOrderId, String selectStatus, Integer currPage);

    /**
     * 批量更改出库单状态（通过出库单的id）检货，包装，发货
     * @param outRepertory
     * @param outRepoOrderId
     * @return String
     */
    String updateOutRepoOrderStatus(OutRepertory outRepertory, List<Integer> outRepoOrderId);

    /**
     * oms通过出库单编号主动取消wms出库单状态
     * @param outRepertory
     * @param outRepoOrderNo
     */
    void omsUpdateOutRepoOrderStatus(OutRepertory outRepertory, List<String> outRepoOrderNo);

    /**
     * 推送出库单时将出库单插入数据库
     * @param outRepertory
     */
    void addOutRepoOrder(OutRepertory outRepertory);

    /**
     * oms取消时根据某个出库单id查询出库单详情
     * @param outRepoOrderNo
     * @return OutRepertory
     */
    OutRepertory outRepoOrderInfo(String outRepoOrderNo);

    /**
     * wms主动取消出库单
     * @param outRepoOrderNo
     * @return String
     */
    String cancelOutRepoOrder(String[] outRepoOrderNo);

    /**
     * 根据出库单id查询某一条出库单信息
     * @param outRepoId
     * @return OutRepertory
     */
    OutRepertory selectByOutRepoId(Integer outRepoId);

    /**
     * 根据出库单id查询某一出库单详情
     * @param outRepoId
     * @return List<OutRepoItem>
     */
    List<OutRepoItem> selectListByOutRepoId(Integer outRepoId);

    /**
     * 发货时需要更新出库单信息（填写发货信息）
     * @param outRepertory
     */
    void updateOutRepoOrder(OutRepertory outRepertory);
}
