package com.connext.wms;

import com.connext.wms.api.dto.CodeTotalStockDTO;
import com.connext.wms.dao.GoodsMapper;
import com.connext.wms.dao.GoodsRepertoryMapper;
import com.connext.wms.dao.RepertoryRegulationMapper;
import com.connext.wms.entity.*;
import com.connext.wms.service.GoodsRepertoryService;
import com.connext.wms.service.GoodsService;
import com.connext.wms.service.RepertoryRegulationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.connext.wms.util.Page.PAGE_SIZE;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestYogurt {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    RepertoryRegulationService repertoryRegulationService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    RepertoryRegulationMapper repertoryRegulationMapper;
    @Autowired
    GoodsRepertoryService goodsRepertoryService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    GoodsRepertoryMapper goodsRepertoryMapper;

    @Test
    public void contextLoads() {
//    goodsMapper.insert(new Goods(1, "1", "1", 1F));
//    goodsMapper.insert(new Goods(2, "2", "2", 1F));
        // GoodsExample goodsExample = new GoodsExample();
        //System.out.println(goodsMapper.countByExample(goodsExample));
         //repertoryRegulationService.deliveryGoodsBeforeDelivery(2,4);
        //repertoryRegulationService.cancelDelivery(1,2);
        //  repertoryRegulationService.deliveryGoodsAfterDelivery(1,1);
        //repertoryRegulationService.rejectedGoodsSuccess(1,1);
        //System.out.println(repertoryRegulationMapper.summaryLockedRepertory());
        //System.out.println(repertoryRegulationMapper.summaryAvailableRepertory());
        //System.out.println(repertoryRegulationMapper.summaryTotalRepertory());
  /*      List<RepertoryRegulation> list = repertoryRegulationMapper.summaryRepertory();
        for (int i = 0; i < list.size(); i++) {
            RepertoryRegulation repertoryRegulation = list.get(i);
            System.out.println(repertoryRegulation.getTotalResult());
            System.out.println(repertoryRegulation.getAvailableResult());
            System.out.println(repertoryRegulation.getGoodsRepertoryId());
            System.out.println(repertoryRegulation.getLockedResult());
        }*/
        //goodsRepertoryService.updateGoodsRepertory();
        //System.out.println();
        System.out.println(goodsMapper.selectByPrimaryKey(1).getGoodsName()+"01");
        //repertoryRegulationService.deliveryGoodsBeforeDelivery(9,40);
    }

    @Test
    public void getOne() {
        //System.out.println(goodsMapper.selectByExample(new GoodsExample()));
        System.out.println(new Double(Math.ceil(15/(double)PAGE_SIZE)).longValue());
        //System.out.println(goodsService.getGoodsBySku("00001"));
        //List<CodeTotalStockDTO> list = goodsRepertoryMapper.getCodeTotalStockDTO();
        //System.out.println(list.toString());
        //System.out.println(goodsService.getGoodsById(1));
        /*List<String> listSku = new ArrayList<>();
        listSku.add("00001");
        listSku.add("00002");
        listSku.add("00003");
        System.out.println(goodsService.getGoodsBySkuList(listSku).toString());*/
        //Goods goods = new Goods(1,"00001","小双汇大王中王火腿肠限量版",33.5f);
        //goodsService.updateGoodsNameAndPrice(goods);
        //System.out.println(goodsRepertoryMapper.getGoodsRepertory().toString());
        //System.out.println(repertoryRegulationMapper.summaryRepertoryByRepertoryId(1));
        /*List<GoodsRepertory> goodsRepertoryList = goodsRepertoryMapper.getGoodsRepertory();
        for (int i = 0; i < goodsRepertoryList.size(); i++) {
            RepertoryRegulation repertoryRegulation = repertoryRegulationMapper.summaryRepertoryByRepertoryId(goodsRepertoryList.get(i).getId());
            Integer realTotalRepertory = goodsRepertoryList.get(i).getTotalNum() + repertoryRegulation.getTotalResult();
            System.out.println(realTotalRepertory);
        }*/
      //  repertoryRegulationService.deliveryGoodsBeforeDelivery(2,50);

       // repertoryRegulationService.replenishRepertory(2,100);
        //List<RealRepertoryVO> list = goodsRepertoryService.getGoodsRepertoryByGoodsName(0,2,"王中王");
       // System.out.println(list.toString());
        GoodsExample example = new GoodsExample();
        System.out.println(goodsMapper.countByExample(example));

    }

    @Test
    public void TestAPI() throws IOException {
        List<CodeTotalStockDTO> list = goodsRepertoryMapper.getCodeTotalStockDTO();
        System.out.println(list.toString());
        // System.out.println(list.toString());
        //String list1 = list.toString();
        //goodsRepertoryService.updateGoodsRepertory();
        //String res = restTemplate.postForObject("http://10.129.100.52:8502/updateTotalStock", list, String.class);
        //System.out.println(res);
        //String jiba =objectMapper.readTree(res).get("message").asText();
        //System.out.println(jiba);

    }


}
