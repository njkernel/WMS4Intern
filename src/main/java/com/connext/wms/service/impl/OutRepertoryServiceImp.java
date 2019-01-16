package com.connext.wms.service.impl;


import com.connext.wms.dao.GoodsMapper;
import com.connext.wms.dao.OutRepertoryDetailMapper;
import com.connext.wms.dao.OutRepertoryMapper;
import com.connext.wms.entity.*;
import com.connext.wms.service.OutRepertoryService;
import com.connext.wms.service.RepertoryRegulationService;
import com.connext.wms.util.Page;
//import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;


/**
 * @author xiamingxing
 * @date 2019/1/7 9:31
 */
@Service
public class OutRepertoryServiceImp implements OutRepertoryService {
    @Autowired
    private OutRepertoryMapper outRepertoryMapper;
    @Autowired
    private OutRepertoryDetailMapper outRepertoryDetailMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RepertoryRegulationService repertoryRegulationService;
    @Autowired
    private GoodsMapper goodsMapper;

    //分页查询出库单
    @Override
    public Page outRepoOrderList(String outRepoOrderId,String selectStatus,Integer currPage) {
        Page page = new Page();
        if (currPage == null) currPage = 1;
        page.setCurrPage(currPage);
        OutRepertoryExample outRepertoryExample=null;
        page.setTotalCount(this.outRepertoryMapper.countByExample(outRepertoryExample));
        page.init();
        page.setData(this.outRepertoryMapper.selectOutRepoByPage(outRepoOrderId,selectStatus,(currPage-1)*Page.PAGE_SIZE,Page.PAGE_SIZE));
        return page;
    }

    //分页查询2
    @Override
    public List<OutRepertory> outRepoOrderListByPage(String outRepoOrderId,String selectStatus,Integer start,Integer size) {
        return this.outRepertoryMapper.selectOutRepoByPage(outRepoOrderId,selectStatus,start,size);
    }

    //批量更新出库单状态
    @Override
    public void updateOutRepoOrderStatus(OutRepertory outRepertory, List<Integer> outRepoOrderId,String[] shippingInfo) {
        OutRepertoryExample outRepertoryExample=new OutRepertoryExample();
        outRepertoryExample.createCriteria().andIdIn(outRepoOrderId);
        List<String> stringList=new ArrayList<String>();
        for(OutRepertory outRepertory1 :this.outRepertoryMapper.selectByExample(outRepertoryExample)){
            stringList.add(outRepertory1.getOrderId());
        }
        Map map=new HashMap();
        map.put("status",outRepertory.getOutRepoStatus());
        map.put("orderIdList",stringList);
        //如果是发货操作
        if(outRepertory.getOutRepoStatus().equals("haveShipped")){
            map.put("shippingInfo",shippingInfo);
            for(OutRepertory outRepertory1 :this.outRepertoryMapper.selectByExample(outRepertoryExample)){
                OutRepertoryDetailExample outRepertoryDetailExample=new OutRepertoryDetailExample();
                outRepertoryDetailExample.or().andOutRepoIdEqualTo(outRepertory1.getId());
                for(OutRepertoryDetail outRepertoryDetail:this.outRepertoryDetailMapper.selectByExample(outRepertoryDetailExample)){
                    this.repertoryRegulationService.deliveryGoodsAfterDelivery(outRepertoryDetail.getGoodsId(),outRepertoryDetail.getGoodsNum());
                }
            }

        }
        /*String s=this.restTemplate.postForObject("http://172.20.10.6:8502/synchronizeState",map,String.class);
        System.out.println("********"+s);
        System.out.println("*******"+outRepertory.getOutRepoStatus());
        if("200".equals(s)){*/
        outRepertory.setReviseTime(new Date());
            this.outRepertoryMapper.updateByExampleSelective(outRepertory, outRepertoryExample);
        //}
    }

    //oms通过出库单编号主动取消wms出库单状态
    @Override
    public void omsUpdateOutRepoOrderStatus(OutRepertory outRepertory, List<String> outRepoOrderNo) {
        OutRepertoryExample outRepertoryExample=new OutRepertoryExample();
        outRepertoryExample.createCriteria().andOutRepoIdIn(outRepoOrderNo);
        this.outRepertoryMapper.updateByExampleSelective(outRepertory,outRepertoryExample);
    }


    //推送出库单时将出库单插入数据库
    @Override
    public void addOutRepoOrder(OutRepertory outRepertory) {
        this.outRepertoryMapper.insert(outRepertory);
    }

    //oms取消时根据某个出库单编号查询出库单详情
    @Override
    public OutRepertory outRepoOrderInfo(String outRepoOrderNO) {
        return this.outRepertoryMapper.selectByOutRepoOrderNo(outRepoOrderNO);
    }

    //wms主动取消出库单
    @Override
    public void cancelOutRepoOrder(String[] outRepoOrderNo) {
        StringBuffer stringBuffer=new StringBuffer();
        for (String str : outRepoOrderNo) {
            stringBuffer.append(str);
            stringBuffer.append(",");
        }
        String newStringBuffer=stringBuffer.toString();
        String outputCodeList = newStringBuffer.substring(0,newStringBuffer.length()-1);
        String s=this.restTemplate.postForObject("http://10.129.100.115:8502/cancelOrderOfWms",outputCodeList,String.class);
        if("200".equals(s)){
            OutRepertory outRepertory=new OutRepertory();
            outRepertory.setOutRepoStatus("have canceled");
            OutRepertoryExample outRepertoryExample=new OutRepertoryExample();
            outRepertoryExample.createCriteria().andOutRepoIdIn(Arrays.asList(outRepoOrderNo));
            this.outRepertoryMapper.updateByExampleSelective(outRepertory,outRepertoryExample);
        }

    }

    //根据出库单id查询某一条出库单信息
    @Override
    public OutRepertory selectByOutRepoId(Integer outRepoId) {
        return this.outRepertoryMapper.selectByPrimaryKey(outRepoId);
    }

    @Override
    public List<OutRepertoryDetail> selectListByOutRepoId(Integer outRepoId) {
        OutRepertoryDetailExample example=new OutRepertoryDetailExample();
        example.or().andOutRepoIdEqualTo(outRepoId);
        return this.outRepertoryDetailMapper.selectByExample(example);
    }


}
