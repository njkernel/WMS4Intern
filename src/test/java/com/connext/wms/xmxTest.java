package com.connext.wms;

import com.connext.wms.dao.OutRepertoryDetailMapper;
import com.connext.wms.dao.OutRepertoryMapper;
import com.connext.wms.entity.OutRepertory;
import com.connext.wms.entity.OutRepertoryDetailExample;
import com.connext.wms.entity.OutRepertoryExample;
import com.connext.wms.service.OutRepertoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class xmxTest {

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
    public void sendStatus(){
        OutRepertory outRepertory=new OutRepertory();
        outRepertory.setOutRepoStatus("have checked");
        List<Integer> list=new ArrayList<Integer>();
        list.add(41);
        String[] shippingInfo=null;
        //String[] shippingInfo={"shentong","1234324",new Date().toString(),new Date().toString()};
        this.outRepertoryService.updateOutRepoOrderStatus(outRepertory,list,shippingInfo);
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

    /*@Test
    public void PageLook(){
        System.out.println(this.outRepertoryService.outRepoOrderListByPage(3,2));

    }*/

}

