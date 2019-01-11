package com.connext.wms.service.impl;

import com.connext.wms.api.dto.CodeTotalStockDTO;
import com.connext.wms.dao.GoodsMapper;
import com.connext.wms.dao.GoodsRepertoryMapper;
import com.connext.wms.dao.RepertoryRegulationMapper;
import com.connext.wms.entity.GoodsExample;
import com.connext.wms.entity.GoodsRepertory;
import com.connext.wms.entity.RealRepertoryVO;
import com.connext.wms.entity.RepertoryRegulation;
import com.connext.wms.service.GoodsRepertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Override
    public void updateGoodsRepertory() {
        List<RepertoryRegulation> list = repertoryRegulationMapper.summaryRepertory();
        for (int i = 0; i < list.size(); i++) {
            RepertoryRegulation repertoryRegulation = list.get(i);
            Integer tnum = repertoryRegulation.getTotalResult();
            Integer anum = repertoryRegulation.getAvailableResult();
            Integer lnum = repertoryRegulation.getLockedResult();
            Integer id = repertoryRegulation.getGoodsRepertoryId();
            goodsRepertoryMapper.updateGoodsRepertory(tnum, anum, lnum, id);
        }
        /*
        清空原库存增减表
         */
        repertoryRegulationMapper.emptyRepertoryRegulation();
        /*
        调用OMS接口，将总库存同步给OMS
         */
        List<CodeTotalStockDTO> listCodeTotalStockDTO = goodsRepertoryMapper.getCodeTotalStockDTO();
        restTemplate.postForObject("http://10.129.100.107:8502/updateTotalStock", listCodeTotalStockDTO, String.class);
    }

    @Override
    public List<RealRepertoryVO> showRealRepertory() {
        List<GoodsRepertory> goodsRepertoryList = goodsRepertoryMapper.getGoodsRepertory();
        List<RealRepertoryVO> list = new ArrayList<>();
        for (int i = 0; i < goodsRepertoryList.size(); i++) {
            RepertoryRegulation repertoryRegulation = repertoryRegulationMapper.summaryRepertoryByRepertoryId(goodsRepertoryList.get(i).getId());
            Integer id = goodsRepertoryList.get(i).getGoodsId();
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andIdEqualTo(id);
            String sku = goodsMapper.selectByExample(goodsExample).get(0).getSku();
            String goodsName = goodsMapper.selectByExample(goodsExample).get(0).getGoodsName();
            Integer realTotalRepertory = goodsRepertoryList.get(i).getTotalNum() + repertoryRegulation.getTotalResult();
            Integer realAvailableRepertory = goodsRepertoryList.get(i).getAvailableNum() + repertoryRegulation.getAvailableResult();
            Integer realLockedRepertory = goodsRepertoryList.get(i).getLockedNum() + repertoryRegulation.getLockedResult();
            RealRepertoryVO realRepertoryVO = new RealRepertoryVO();
            realRepertoryVO.setSku(sku);
            realRepertoryVO.setGoodsName(goodsName);
            realRepertoryVO.setRealAvailableRepertory(realAvailableRepertory);
            realRepertoryVO.setRealLockedRepertory(realLockedRepertory);
            realRepertoryVO.setRealTotalRepertory(realTotalRepertory);
            list.add(realRepertoryVO);
        }
        return list;

    }
}
