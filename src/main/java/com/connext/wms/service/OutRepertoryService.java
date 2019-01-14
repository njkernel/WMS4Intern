package com.connext.wms.service;

import com.connext.wms.entity.OutRepertory;
import com.connext.wms.entity.OutRepertoryDetail;
import com.connext.wms.entity.OutRepertoryExample;
import com.connext.wms.util.Page;

import java.util.List;


/**
 * @author xiamingxing
 * @date 2019/1/7 9:31
 */
public interface OutRepertoryService {
    //分页查询出库单
    //Page outRepoOrderList(Integer currPage, OutRepertoryExample example);
    //分页查询2
    List<OutRepertory> outRepoOrderListByPage(Integer start,Integer size);
    //更改出库单状态（通过出库单的id）
    void updateOutRepoOrderStatus(OutRepertory outRepertory, List<Integer> outRepoOrderId,String[] shippingInfo);
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
    //根据出库单id查询某一条出库单信息
    List<OutRepertoryDetail> selectListByOutRepoId(Integer outRepoId);
}
