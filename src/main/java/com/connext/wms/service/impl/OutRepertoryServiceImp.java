package com.connext.wms.service.impl;

import com.connext.wms.dao.GoodsMapper;
import com.connext.wms.dao.OutRepertoryDetailMapper;
import com.connext.wms.dao.OutRepertoryMapper;
import com.connext.wms.entity.*;
import com.connext.wms.service.OutRepertoryService;
import com.connext.wms.service.RepertoryRegulationService;
import com.connext.wms.util.Constant;
import com.connext.wms.util.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;


/**
 * @author xiamingxing
 * @date 2019/1/7 9:31
 */
@Service
public class OutRepertoryServiceImp implements OutRepertoryService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OutRepertoryMapper outRepertoryMapper;
    @Autowired
    private OutRepertoryDetailMapper outRepertoryDetailMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RepertoryRegulationService repertoryRegulationService;
    @Autowired
    Constant constant;

    /**
     * 分页查询出库单
     *
     * @param currPage
     * @return Page
     */
    @Override
    public Page outRepoOrderList(Integer currPage) {
        Page page = new Page();
        if (currPage == null) currPage = 1;
        page.setCurrPage(currPage);
        OutRepertoryExample outRepertoryExample = new OutRepertoryExample();
        outRepertoryExample.or().andOutRepoStatusNotEqualTo("shipException");
        page.setTotalCount(this.outRepertoryMapper.countByExample(outRepertoryExample));
        page.init();
        page.setData(this.outRepertoryMapper.selectOutRepoByPage((currPage - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE));
        return page;
    }

    /**
     * 模糊查询
     *
     * @param outRepoOrderId
     * @param selectStatus
     * @param currPage
     * @return Page
     */
    @Override
    public Page unclearSelect(String outRepoOrderId, String selectStatus, Integer currPage) {
        Page page = new Page();
        if (currPage == null) currPage = 1;
        page.setCurrPage(currPage);
        page.setTotalCount(this.outRepertoryMapper.countByConditions(outRepoOrderId, selectStatus));
        page.init();
        PageHelper.startPage(currPage, Page.PAGE_SIZE);
        page.setData(this.outRepertoryMapper.unclearSelect(outRepoOrderId, selectStatus));
        return page;
    }

    /**
     * 批量更改出库单状态（通过出库单的id）检货，包装，发货
     *
     * @param outRepertory
     * @param outRepoOrderId
     * @return String
     */
    @Override
    public String updateOutRepoOrderStatus(OutRepertory outRepertory, List<Integer> outRepoOrderId) {
        OutRepertoryExample outRepertoryExample = new OutRepertoryExample();
        outRepertoryExample.createCriteria().andIdIn(outRepoOrderId);
        List<OutRepertory> outRepertoryList = this.outRepertoryMapper.selectByExample(outRepertoryExample);
        List<String> stringList = new ArrayList<String>();
        for (OutRepertory outRepertory1 : outRepertoryList) {
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
        }
        String s = "202";
        try {
            s = this.restTemplate.postForObject(constant.OUT_UPDATE_URL, map, String.class);
            if ("200".equals(s)) {
                outRepertory.setReviseTime(new Date());
                outRepertory.setSyncStatus("haveSync");
                this.outRepertoryMapper.updateByExampleSelective(outRepertory, outRepertoryExample);
                if (outRepertory.getOutRepoStatus().equals("haveShipped")) {
                    for (OutRepertory outRepertory1 : this.outRepertoryMapper.selectByExample(outRepertoryExample)) {
                        OutRepertoryDetailExample outRepertoryDetailExample = new OutRepertoryDetailExample();
                        outRepertoryDetailExample.or().andOutRepoIdEqualTo(outRepertory1.getId());
                        for (OutRepertoryDetail outRepertoryDetail : this.outRepertoryDetailMapper.selectByExample(outRepertoryDetailExample)) {
                            this.repertoryRegulationService.deliveryGoodsAfterDelivery(outRepertoryDetail.getGoodsId(), outRepertoryDetail.getGoodsNum());
                        }
                    }

                }
            }
            return s;
        } catch (RestClientException e) {
            OutRepertory outRepertory1 = new OutRepertory();
            outRepertory1.setSyncStatus("notSync");
            if (("haveShipped").equals(outRepertory.getOutRepoStatus())) {
                outRepertory1.setOutRepoStatus("shipException");
            }
            this.outRepertoryMapper.updateByExampleSelective(outRepertory1, outRepertoryExample);
            return s;
        }

    }

    /**
     * oms通过出库单编号主动取消wms出库单状态
     *
     * @param outRepertory
     * @param outRepoOrderNo
     */
    @Override
    public void omsUpdateOutRepoOrderStatus(OutRepertory outRepertory, List<String> outRepoOrderNo) {
        OutRepertoryExample outRepertoryExample = new OutRepertoryExample();
        outRepertoryExample.createCriteria().andOutRepoIdIn(outRepoOrderNo);
        this.outRepertoryMapper.updateByExampleSelective(outRepertory, outRepertoryExample);
    }


    /**
     * 推送出库单时将出库单插入数据库
     *
     * @param outRepertory
     */
    @Override
    public void addOutRepoOrder(OutRepertory outRepertory) {
        this.outRepertoryMapper.insert(outRepertory);
    }

    /**
     * oms取消时根据某个出库单id查询出库单详情
     *
     * @param outRepoOrderNO
     * @return OutRepertory
     */
    @Override
    public OutRepertory outRepoOrderInfo(String outRepoOrderNO) {
        return this.outRepertoryMapper.selectByOutRepoOrderNo(outRepoOrderNO);
    }

    /**
     * wms主动取消出库单
     *
     * @param outRepoOrderNo
     * @return String
     */
    @Override
    public String cancelOutRepoOrder(String[] outRepoOrderNo) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : outRepoOrderNo) {
            stringBuffer.append(str);
            stringBuffer.append(",");
        }
        String newStringBuffer = stringBuffer.toString();
        String outputCodeList = newStringBuffer.substring(0, newStringBuffer.length() - 1);

        String s = "202";
        try {
            s = this.restTemplate.postForObject(constant.CANCEL_OUT_URL, outputCodeList, String.class);
            if ("200".equals(s)) {
                OutRepertory outRepertory = new OutRepertory();
                outRepertory.setOutRepoStatus("haveCanceled");
                outRepertory.setReviseTime(new Date());
                outRepertory.setSyncStatus("haveSync");
                //取消出库单时，更新库存
                for (String outRepoNo : outRepoOrderNo) {
                    outRepertory.setOutRepoId(outRepoNo);
                    //更新 状态为已取消
                    this.outRepertoryMapper.updateWhenCancel(outRepertory);
                    OutRepertoryDetailExample outRepertoryDetailExample = new OutRepertoryDetailExample();
                    outRepertoryDetailExample.or().andOutRepoIdEqualTo(this.outRepertoryMapper.selectByOutRepoOrderNo(outRepoNo).getId());
                    for (OutRepertoryDetail outRepertoryDetail : this.outRepertoryDetailMapper.selectByExample(outRepertoryDetailExample)) {
                        this.repertoryRegulationService.cancelDelivery(outRepertoryDetail.getGoodsId(), outRepertoryDetail.getGoodsNum());
                    }
                }
            }
            return s;
        } catch (RestClientException e) {
            OutRepertory outRepertory1 = new OutRepertory();
            outRepertory1.setSyncStatus("notSync");
            OutRepertoryExample outRepertoryExample = new OutRepertoryExample();
            List<String> list = Arrays.asList(outRepoOrderNo);
            outRepertoryExample.or().andOutRepoIdIn(list);
            this.outRepertoryMapper.updateByExampleSelective(outRepertory1, outRepertoryExample);
            return s;
        }


    }

    /**
     * 根据出库单id查询某一条出库单信息
     *
     * @param outRepoId
     * @return OutRepertory
     */
    @Override
    public OutRepertory selectByOutRepoId(Integer outRepoId) {
        return this.outRepertoryMapper.selectByPrimaryKey(outRepoId);
    }


    /**
     * 根据出库单id查询某一出库单详情
     *
     * @param outRepoId
     * @return List<OutRepoItem>
     */
    @Override
    public List<OutRepoItem> selectListByOutRepoId(Integer outRepoId) {
        List<OutRepoItem> outRepoItemList = new ArrayList<OutRepoItem>();
        OutRepertoryDetailExample example = new OutRepertoryDetailExample();
        example.or().andOutRepoIdEqualTo(outRepoId);
        for (OutRepertoryDetail outRepertoryDetail : this.outRepertoryDetailMapper.selectByExample(example)) {
            //商品id
            outRepertoryDetail.getGoodsId();
            Goods goods = this.goodsMapper.selectByPrimaryKey(outRepertoryDetail.getGoodsId());
            OutRepoItem outRepoItem = new OutRepoItem(goods.getSku(), goods.getGoodsName(), goods.getGoodsPrice(), outRepertoryDetail.getGoodsNum());
            outRepoItemList.add(outRepoItem);
        }
        return outRepoItemList;

    }

    /**
     * 发货时需要更新出库单信息（填写发货信息）
     *
     * @param outRepertory
     */
    @Override
    public void updateOutRepoOrder(OutRepertory outRepertory) {
        this.outRepertoryMapper.updateByPrimaryKeySelective(outRepertory);
    }


}
