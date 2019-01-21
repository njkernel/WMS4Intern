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
    //分页查询出库单
    Page outRepoOrderList(Integer currPage);
    //模糊查询
    Page unclearSelect(String outRepoOrderId,String selectStatus,Integer currPage);
    //批量更改出库单状态（通过出库单的id）检货，包装，发货
    void updateOutRepoOrderStatus(OutRepertory outRepertory, List<Integer> outRepoOrderId);
    //oms通过出库单编号主动取消wms出库单状态
    void omsUpdateOutRepoOrderStatus(OutRepertory outRepertory,List<String> outRepoOrderNo);
    //推送出库单时将出库单插入数据库
    void addOutRepoOrder(OutRepertory outRepertory);
    //oms取消时根据某个出库单id查询出库单详情
    OutRepertory outRepoOrderInfo(String outRepoOrderNo);
    //wms主动取消出库单
    void cancelOutRepoOrder(String[] outRepoOrderNo);
    //根据出库单id查询某一条出库单信息
    OutRepertory selectByOutRepoId(Integer outRepoId);
    //根据出库单id查询某一出库单详情
    List<OutRepoItem> selectListByOutRepoId(Integer outRepoId);
    //发货时需要更新出库单信息（填写发货信息）
    void updateOutRepoOrder(OutRepertory outRepertory);
}
