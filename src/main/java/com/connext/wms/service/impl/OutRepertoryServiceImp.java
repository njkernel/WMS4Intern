package com.connext.wms.service.impl;

import com.connext.wms.dao.OutRepertoryDetailMapper;
import com.connext.wms.dao.OutRepertoryMapper;
import com.connext.wms.entity.*;
import com.connext.wms.service.OutRepertoryService;
import com.connext.wms.service.RepertoryRegulationService;
import com.connext.wms.util.Page;
import com.github.pagehelper.PageHelper;
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


    //分页查询出库单
    @Override
    public Page outRepoOrderList(Integer currPage) {
        Page page = new Page();
        if (currPage == null) currPage = 1;
        page.setCurrPage(currPage);
        OutRepertoryExample outRepertoryExample = null;
        page.setTotalCount(this.outRepertoryMapper.countByExample(outRepertoryExample));
        page.init();
        page.setData(this.outRepertoryMapper.selectOutRepoByPage((currPage - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE));
        return page;
    }

    //分页查询2
    @Override
    public Page unclearSelect(String outRepoOrderId, String selectStatus, Integer currPage) {
        OutRepertoryExample outRepertoryExample=new OutRepertoryExample();
        Page page = new Page();
        if (currPage == null) currPage = 1;
        page.setCurrPage(currPage);
        outRepertoryExample.or().andOutRepoIdLike("%"+outRepoOrderId+"%").andOutRepoStatusEqualTo(selectStatus);
        page.setTotalCount(this.outRepertoryMapper.countByExample(outRepertoryExample));
        page.init();
        PageHelper.startPage(currPage, Page.PAGE_SIZE);
        page.setData(this.outRepertoryMapper.selectByExample(outRepertoryExample));
        return page;
    }

    //批量更新出库单状态
    @Override
    public void updateOutRepoOrderStatus(OutRepertory outRepertory, List<Integer> outRepoOrderId) {
        OutRepertoryExample outRepertoryExample = new OutRepertoryExample();
        outRepertoryExample.createCriteria().andIdIn(outRepoOrderId);
        List<OutRepertory> outRepertoryList=this.outRepertoryMapper.selectByExample(outRepertoryExample);
        List<String> stringList = new ArrayList<String>();
        for(OutRepertory outRepertory1:outRepertoryList){
            stringList.add(outRepertory1.getOrderId());
        }
        Map map = new HashMap();
        map.put("status", outRepertory.getOutRepoStatus());
        map.put("orderIdList", stringList);
        //如果是发货操作
        if (outRepertory.getOutRepoStatus().equals("haveShipped")) {
            String[] shippingInfo = new String[3];
            for (OutRepertory outRepertory1 : this.outRepertoryMapper.selectByExample(outRepertoryExample)) {
                //stringList.add(outRepertory1.getOrderId());
                shippingInfo[0] = outRepertory1.getExpressCompany();
                shippingInfo[1] = outRepertory1.getExpressId();
                shippingInfo[2] = outRepertory1.getReviseTime().toString();
            }
            map.put("shippingInfo", shippingInfo);
            for (OutRepertory outRepertory1 : this.outRepertoryMapper.selectByExample(outRepertoryExample)) {
                OutRepertoryDetailExample outRepertoryDetailExample = new OutRepertoryDetailExample();
                outRepertoryDetailExample.or().andOutRepoIdEqualTo(outRepertory1.getId());
                for (OutRepertoryDetail outRepertoryDetail : this.outRepertoryDetailMapper.selectByExample(outRepertoryDetailExample)) {
                    this.repertoryRegulationService.deliveryGoodsAfterDelivery(outRepertoryDetail.getGoodsId(), outRepertoryDetail.getGoodsNum());
                }
            }

        }
        String s = this.restTemplate.postForObject("http://10.129.100.40:8502/synchronizeState", map, String.class);
        System.out.println("********" + s);
        System.out.println("*******" + outRepertory.getOutRepoStatus());
        if ("200".equals(s)) {
            outRepertory.setReviseTime(new Date());
            this.outRepertoryMapper.updateByExampleSelective(outRepertory, outRepertoryExample);
        } else if ("201".equals(s)) {
            outRepertory.setOutRepoStatus("shipException");
            this.outRepertoryMapper.updateByExampleSelective(outRepertory, outRepertoryExample);
        }
    }

    //oms通过出库单编号主动取消wms出库单状态
    @Override
    public void omsUpdateOutRepoOrderStatus(OutRepertory outRepertory, List<String> outRepoOrderNo) {
        OutRepertoryExample outRepertoryExample = new OutRepertoryExample();
        outRepertoryExample.createCriteria().andOutRepoIdIn(outRepoOrderNo);
        this.outRepertoryMapper.updateByExampleSelective(outRepertory, outRepertoryExample);
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
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : outRepoOrderNo) {
            stringBuffer.append(str);
            stringBuffer.append(",");
        }
        String newStringBuffer = stringBuffer.toString();
        String outputCodeList = newStringBuffer.substring(0, newStringBuffer.length() - 1);
        String s = this.restTemplate.postForObject("http://10.129.100.79:8502/cancelOrderOfWms", outputCodeList, String.class);
        if ("200".equals(s)) {
            OutRepertory outRepertory = new OutRepertory();
            outRepertory.setOutRepoStatus("haveCanceled");
            outRepertory.setReviseTime(new Date());
            /*OutRepertoryExample outRepertoryExample = new OutRepertoryExample();
            outRepertoryExample.createCriteria().andOutRepoIdIn(Arrays.asList(outRepoOrderNo));
            this.outRepertoryMapper.updateByExample(outRepertory, outRepertoryExample);*/
            //取消出库单时，更新库存
            for (String outRepoNo : outRepoOrderNo) {
                outRepertory.setOutRepoId(outRepoNo);
                this.outRepertoryMapper.updateWhenCancel(outRepertory);//更新 状态为已取消；
                OutRepertoryDetailExample outRepertoryDetailExample = new OutRepertoryDetailExample();
                outRepertoryDetailExample.or().andOutRepoIdEqualTo(this.outRepertoryMapper.selectByOutRepoOrderNo(outRepoNo).getId());
                for (OutRepertoryDetail outRepertoryDetail : this.outRepertoryDetailMapper.selectByExample(outRepertoryDetailExample)) {
                    this.repertoryRegulationService.cancelDelivery(outRepertoryDetail.getGoodsId(), outRepertoryDetail.getGoodsNum());
                }
                Integer outRepoId=this.outRepertoryMapper.selectByOutRepoOrderNo(outRepoNo).getId();//根据某个出库单号获取某一个出库单id
                for(OutRepertoryDetail outRepertoryDetail:this.outRepertoryDetailMapper.selectListByOutRepoId(outRepoId)){
                    System.out.println("@@@@@@@@@@@@"+outRepertoryDetail);
                    System.out.println(outRepertoryDetail.getGoodsId()+"^%^%^^%^"+outRepertoryDetail.getGoodsNum());
                    outRepertoryDetail.getGoodsId();
                }
            }
        }

    }

    //根据出库单id查询某一条出库单信息
    @Override
    public OutRepertory selectByOutRepoId(Integer outRepoId) {
        return this.outRepertoryMapper.selectByPrimaryKey(outRepoId);
    }

    @Override
    public List<OutRepertoryDetail> selectListByOutRepoId(Integer outRepoId) {
        OutRepertoryDetailExample example = new OutRepertoryDetailExample();
        example.or().andOutRepoIdEqualTo(outRepoId);
        return this.outRepertoryDetailMapper.selectByExample(example);
    }

    @Override
    public void updateOutRepoOrder(OutRepertory outRepertory) {
        this.outRepertoryMapper.updateByPrimaryKeySelective(outRepertory);
    }


}
