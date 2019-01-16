package com.connext.wms;

import com.connext.wms.api.dto.OutRepoOrderDetailDto;
import com.connext.wms.dao.OutRepertoryDetailMapper;
import com.connext.wms.dao.OutRepertoryMapper;
import com.connext.wms.entity.OutRepertory;
import com.connext.wms.entity.OutRepertoryDetail;
import com.connext.wms.entity.OutRepertoryDetailExample;
import com.connext.wms.entity.OutRepertoryExample;
import com.connext.wms.service.GoodsService;
import com.connext.wms.service.OutRepertoryService;
import com.connext.wms.service.RepertoryRegulationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class xmxTest {
    @Resource
    private GoodsService goodsService;
    @Resource
    private RepertoryRegulationService repertoryRegulationService;
    @Autowired
    private OutRepertoryMapper outRepertoryMapper;
    @Autowired
    private OutRepertoryDetailMapper outRepertoryDetailMapper;
    @Autowired
    private OutRepertoryDetailExample outRepertoryDetailExample;
    @Autowired
    private OutRepertoryExample outRepertoryExample;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OutRepertoryService outRepertoryService;


    @Test
    public void updateOutRepoOrderStatus() {
        OutRepertory outRepertory=new OutRepertory();
        outRepertory.setOutRepoStatus("一旦发生");
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        outRepertoryExample.createCriteria().andIdIn(integerList);
        this.outRepertoryMapper.updateByExampleSelective(outRepertory,outRepertoryExample);
    }

    @Test
    public void outRepoOrderDetail(){

        this.outRepertoryDetailExample.createCriteria().andOutRepoIdEqualTo(1);
        System.out.println(this.outRepertoryDetailMapper.selectByExample(outRepertoryDetailExample));
        OutRepertory outRepertory=this.outRepertoryMapper.selectByPrimaryKey(1);
        outRepertory.setOutRepertoryDetailList(this.outRepertoryDetailMapper.selectByExample(outRepertoryDetailExample));
        System.out.println(outRepertory);
    }

    @Test
    public void addOutRepoOrder(){
        OutRepertory outRepertory=new OutRepertory();
        outRepertory.setOrderId("123456");
        outRepertory.setChannelId("123456");
        outRepertory.setCreateTime(new Date());
        this.outRepertoryMapper.insert(outRepertory);
    }

    @Test
    public void Testt(){
        String outRepoId="1234567";
        String orderId="1234567";
        String channelId="1234567";
        String receiverName="1234567";
        String receiverAddress="1234567";
        OutRepoOrderDetailDto outRepertoryDetailDto1=new OutRepoOrderDetailDto();
        outRepertoryDetailDto1.setGoodsCode("S0001");
        outRepertoryDetailDto1.setNum(12);
        OutRepoOrderDetailDto outRepertoryDetailDto2=new OutRepoOrderDetailDto();
        outRepertoryDetailDto2.setGoodsCode("S0002");
        outRepertoryDetailDto2.setNum(20);
        List<OutRepoOrderDetailDto> list=new ArrayList<>();
        list.add(outRepertoryDetailDto1);
        list.add(outRepertoryDetailDto2);
        Map map=new HashMap();
        map.put("outRepoId",outRepoId);
        map.put("orderId",orderId);
        map.put("channelId",channelId);
        map.put("receiverName",receiverName);
        map.put("receiverAddress",receiverAddress);
        map.put("outRepoOrderDetailDto",list);
        this.restTemplate.postForObject("http://localhost:8080/api/pushOutRepoOrder",map,String.class);

    }

    @Test
    public void receiveIdWhenSelect(){
        OutRepertory outRepertory=new OutRepertory();
        outRepertory.setOutRepoStatus("waittingChecked");
        outRepertory.setOrderId("1234567");
        outRepertory.setExpressId("12345678");
        Integer i=this.outRepertoryMapper.insert(outRepertory);
        System.out.println(outRepertory.getId());
        System.out.println(i);
    }

    @Test
    public void TestGood(){
        System.out.println(this.goodsService.getGoodsBySku("S0007").getId());
        this.repertoryRegulationService.deliveryGoodsBeforeDelivery(8,1000);

    }

    @Test
    public void cancelResult(){
        OutRepertory outRepertory=new OutRepertory();
        outRepertory.setId(1);
        OutRepertory outRepertory1=new OutRepertory();
        outRepertory1.setId(2);
        List<OutRepertory> outRepertoryList=new ArrayList<>();
        outRepertoryList.add(outRepertory);
        outRepertoryList.add(outRepertory1);
        List<Integer> integerList=new ArrayList<Integer>();
        Iterator iter = outRepertoryList.iterator();
        while(iter.hasNext()) {
            OutRepertory newOut=(OutRepertory) iter.next();
            integerList.add(newOut.getId());
        }
        System.out.println(integerList);
        OutRepertory outRepertory2=new OutRepertory();
        outRepertory2.setOutRepoStatus("xmx");
        outRepertoryExample.createCriteria().andIdIn(integerList);
        this.outRepertoryMapper.updateByExampleSelective(outRepertory2,outRepertoryExample);
    }

    @Test
    public void cancelOutRepoOrder(){
        String[] stringArray=null;
        this.outRepertoryService.cancelOutRepoOrder(stringArray);
    }

    @Test
    public void TestTTT(){
        List<OutRepertoryDetail> outRepertoryDetailList=new ArrayList<>();
        OutRepertoryDetail outRepertoryDetail=new OutRepertoryDetail();
        outRepertoryDetail.setId(100);
        outRepertoryDetail.setGoodsName("dfdf");
        outRepertoryDetail.setGoodsId(100);
        outRepertoryDetail.setOutRepoId(99);
        System.out.println(outRepertoryDetail);
        outRepertoryDetailList.add(outRepertoryDetail);
        this.outRepertoryDetailMapper.insertSelective(outRepertoryDetail);
        //this.outRepertoryDetailMapper.insertDetailList(outRepertoryDetailList);
    }

    @Test
    public void sendStatus(){
        OutRepertory outRepertory=new OutRepertory();
        outRepertory.setOutRepoStatus("have checked");
        List<Integer> list=new ArrayList<Integer>();
        list.add(41);
        String[] shippingInfo=null;
        //String[] shippingInfo={"shentong","1234324",new Date().toString(),new Date().toString()};
        this.outRepertoryService.updateOutRepoOrderStatus(outRepertory,list);
    }
    @Test
    public void testServer(){
        String status="哈哈";
        String shippingInfo="new york";
        List<String> list=new ArrayList<String>();
        list.add("12345");
        list.add("12345678");
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("status",status);
        map.put("orderIdList",list);
        map.put("shippingInfo",shippingInfo);
        this.restTemplate.postForObject("http://localhost:8080/api/test",map,String.class);
    }

    @Test
    public void cancelDelivery(){
        this.repertoryRegulationService.cancelDelivery(8,90);
    }

    /*@Test
    public void PageLook(){
        System.out.println(this.outRepertoryService.outRepoOrderListByPage(3,2));

    }*/

    @Test
    public void tttest(){
        String[] outRepoOrderNo={"1901060001a5baee9","1901060002bb0dc48"};
        this.outRepertoryService.cancelOutRepoOrder(outRepoOrderNo);
    }

    @Test
    public void TTesttt(){
        OutRepertoryExample outRepertoryExample=new OutRepertoryExample();
        outRepertoryExample.or().andOutRepoIdLike("%ee%").andOutRepoStatusEqualTo("waittingChecked");
        System.out.println(this.outRepertoryMapper.countByExample(outRepertoryExample));
    }

}

