package com.connext.wms.service.impl;

import com.connext.wms.api.dto.CodeTotalStockDTO;
import com.connext.wms.dao.GoodsMapper;
import com.connext.wms.dao.GoodsRepertoryMapper;
import com.connext.wms.dao.RepertoryRegulationMapper;
import com.connext.wms.entity.*;
import com.connext.wms.service.GoodsRepertoryService;
import com.connext.wms.util.Constant;
import com.connext.wms.util.Page;
import com.connext.wms.util.PageSet;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/8 16:40
 */
@Service
public class GoodsRepertoryServiceImpl implements GoodsRepertoryService {

    @Autowired
    RepertoryRegulationMapper repertoryRegulationMapper;

    @Autowired
    GoodsRepertoryMapper goodsRepertoryMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    Constant constant;


    @Override
    public void updateGoodsRepertory() {
        List<RepertoryRegulation> list = repertoryRegulationMapper.summaryRepertory();
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                RepertoryRegulation repertoryRegulation = list.get(i);
                Integer tNum = repertoryRegulation.getTotalResult();
                Integer aNum = repertoryRegulation.getAvailableResult();
                Integer lNum = repertoryRegulation.getLockedResult();
                Integer id = repertoryRegulation.getGoodsRepertoryId();
                goodsRepertoryMapper.updateGoodsRepertory(tNum, aNum, lNum, id);
            }
        }

        /*
        清空原库存增减表
         */
        repertoryRegulationMapper.emptyRepertoryRegulation();
        /*
        调用OMS接口，将总库存同步给OMS
         */

        List<CodeTotalStockDTO> listCodeTotalStockDTO = goodsRepertoryMapper.getCodeTotalStockDTO();
        try {
            restTemplate.postForObject(constant.GOODS_TOTAL_URL, listCodeTotalStockDTO, String.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用OMS接口，将单个商品的总库存同步给OMS
     *
     * @return
     */
    @Override
    public String synchronizeRepertory(Integer id) {
        /**
         * 更新商品库存表中该id的商品库存
         */
        String flag = "success";
        RepertoryRegulation repertoryRegulation = repertoryRegulationMapper.summaryRepertoryByRepertoryId(id);
        if (repertoryRegulation == null) {
            //flag = "error";
            try {
                List<CodeTotalStockDTO> listCodeTotalStockDTO = goodsRepertoryMapper.getCodeTotalStockDTOById(id);
                restTemplate.postForObject(constant.GOODS_TOTAL_URL, listCodeTotalStockDTO, String.class);
            } catch (Exception e) {
                flag = "fail";//todo
            }
        } else {
            Integer tNum = repertoryRegulation.getTotalResult();
            Integer aNum = repertoryRegulation.getAvailableResult();
            Integer lNum = repertoryRegulation.getLockedResult();
            //System.out.println(tNum+","+aNum+","+lNum);
            goodsRepertoryMapper.updateGoodsRepertory(tNum, aNum, lNum, id);
            /**
             * 删除增减表中该id的增减记录
             */
            repertoryRegulationMapper.deleteRepertoryByRepertoryId(id);

            /**
             * 调用OMS接口,同步
             */
            try {
                List<CodeTotalStockDTO> listCodeTotalStockDTO = goodsRepertoryMapper.getCodeTotalStockDTOById(id);
                restTemplate.postForObject(constant.GOODS_TOTAL_URL, listCodeTotalStockDTO, String.class);
            } catch (Exception e) {
                flag = "fail";//todo
            }
        }

        return flag;

    }

    @Override
    public Page showRealRepertory(Integer currPage, String key) {
        //Page page = new Page();
       /* if (currPage == null) {
            currPage = 1;
        }*/
        //currPage = (currPage == null) ? 1 : currPage;
        //page.setCurrPage(currPage);
        // page.init();
        Long lon = (long) goodsRepertoryMapper.getCount();
        PageHelper.startPage(currPage, Page.PAGE_SIZE);
        //List<RealRepertoryVO> list = goodsRepertoryMapper.getRealVO(key, (currPage - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
        List<RealRepertoryVO> list = goodsRepertoryMapper.getRealVO(key);
        return PageSet.setPage(list, currPage, lon);
        //return PageSet.setPage(list, currPage, (long) goodsRepertoryMapper.getCount());
    }

    /*private List summaryRealRepertory(List<GoodsRepertory> goodsRepertoryList) {
        List<RealRepertoryVO> list = new ArrayList<>();
        for (int i = 0; i < goodsRepertoryList.size(); i++) {
            RepertoryRegulation repertoryRegulation = repertoryRegulationMapper.summaryRepertoryByRepertoryId(goodsRepertoryList.get(i).getId());
            Integer id = goodsRepertoryList.get(i).getGoodsId();
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andIdEqualTo(id);
            Integer goodsRepertoryId = goodsRepertoryList.get(i).getId();
            String sku = goodsMapper.selectByExample(goodsExample).get(0).getSku();
            String goodsName = goodsMapper.selectByExample(goodsExample).get(0).getGoodsName();
            RealRepertoryVO realRepertoryVO = new RealRepertoryVO();
            realRepertoryVO.setId(goodsRepertoryId);
            realRepertoryVO.setSku(sku);
            realRepertoryVO.setGoodsName(goodsName);
            if (repertoryRegulation != null) {
                Integer realTotalRepertory = goodsRepertoryList.get(i).getTotalNum() + repertoryRegulation.getTotalResult();
                Integer realAvailableRepertory = goodsRepertoryList.get(i).getAvailableNum() + repertoryRegulation.getAvailableResult();
                Integer realLockedRepertory = goodsRepertoryList.get(i).getLockedNum() + repertoryRegulation.getLockedResult();
                realRepertoryVO.setRealAvailableRepertory(realAvailableRepertory);
                realRepertoryVO.setRealLockedRepertory(realLockedRepertory);
                realRepertoryVO.setRealTotalRepertory(realTotalRepertory);
                list.add(realRepertoryVO);
            } else {
                Integer realTotalRepertory = goodsRepertoryList.get(i).getTotalNum();
                Integer realAvailableRepertory = goodsRepertoryList.get(i).getAvailableNum();
                Integer realLockedRepertory = goodsRepertoryList.get(i).getLockedNum();
                realRepertoryVO.setRealAvailableRepertory(realAvailableRepertory);
                realRepertoryVO.setRealLockedRepertory(realLockedRepertory);
                realRepertoryVO.setRealTotalRepertory(realTotalRepertory);
                list.add(realRepertoryVO);
            }


        }
        return list;
    }*/

    @Override
    public Page getGoodsRepertoryByGoodsName(Integer currPage, String key) {
        String newKey = "%" + key + "%";
        Page page = new Page();
       /* if (currPage == null) {
            currPage = 1;
        }*/
        currPage = (currPage == null) ? 1 : currPage;
        page.setCurrPage(currPage);
        page.setTotalCount((long) goodsRepertoryMapper.getCountByKey(newKey));
        page.init();
        PageHelper.startPage(currPage, Page.PAGE_SIZE);
        page.setData(goodsRepertoryMapper.getRealVO(key));
       /* List<GoodsRepertory> goodsRepertoryList = goodsRepertoryMapper.getGoodsRepertoryByGoodsName(0, goodsRepertoryMapper.getCountByKey(newKey), newKey);
        List<RealRepertoryVO> list = summaryRealRepertory(goodsRepertoryList);*/
        // System.out.println(goodsRepertoryMapper.getCountByKey(newKey));
        return page;
    }
}
