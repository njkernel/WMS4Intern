package com.connext.wms;
/**
 * @Author: Chao.Sun
 * @Date: 2019/1/7 9:37
 * @Version 1.0
 */

import com.connext.wms.dao.*;
import com.connext.wms.entity.*;
import com.connext.wms.service.ExceptionService;
import com.connext.wms.service.ExpressCompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WmsApplicationTests {
  @Autowired private ExpressCompanyMapper expressCompanyMapper;
  @Autowired private OutRepertoryMapper outRepertoryMapper;
  @Autowired private ExceptionService exceptionService;
  @Autowired private ExpressCompanyService expressCompanyService;


  //添加快递公司
  @Test
  public void insert(){
    String expressCompanyName = "天天快递";
    String contactWay = "3333333333";
    expressCompanyService.insert(expressCompanyName,contactWay);
    System.out.println("添加成功");
  }

  @Test
  //删除快递公司信息
  public void deleteByExample(){
    String expressCompanyName = "顺丰快递公司";
    ExpressCompanyExample example = new ExpressCompanyExample();
    example.createCriteria().andExpressCompanyNameEqualTo(expressCompanyName);
    expressCompanyMapper.deleteByExample(example);
    System.out.println("删除成功");
  }

  @Test
  //修改快递公司信息
  public void updateByExample(){
        String newname="顺丰快递公司1";
        ExpressCompany record = new ExpressCompany();
        record.setExpressCompanyName("顺丰快递公司1");
        record.setContactWay("2333332");
        ExpressCompanyExample example = new ExpressCompanyExample();
        example.createCriteria().andExpressCompanyNameEqualTo(newname);
        expressCompanyMapper.updateByExample(record,example);
        System.out.println("修改成功");

  }

  @Test
  public void selectByExample(){
    ExpressCompanyExample example = new ExpressCompanyExample();
    expressCompanyMapper.selectByExample(example);
    System.out.println(expressCompanyMapper.selectByExample(example));
  }

  @Test
  //根据关键字查询
  public void selectByExample1(){
    String key = "%" + "圆通" + "%";
    ExpressCompanyExample example = new ExpressCompanyExample();
    example.createCriteria().andExpressCompanyNameLike(key);
    System.out.println(expressCompanyMapper.selectByExample(example));
  }

  @Test
  //查找所有异常订单
  public void selectByExample2(){
    String Key = "%快递%";
    OutRepertoryExample example = new OutRepertoryExample();
    example.createCriteria().andExpressCompanyLike(Key);
    System.out.println(outRepertoryMapper.selectByExample(example));
  }


  @Test
  public void test3(){
    String newKey = "%" + "233" + "%";
    System.out.println(exceptionService.selectByExampleToKey(1,newKey));
  }

  @Test
  public void test4(){
    System.out.println(expressCompanyService.findAll());
  }

  @Test
  public  void test5(){
    System.out.println(expressCompanyService.findById(64));
  }

  @Test
  public void test6(){
    Integer id = 64;
    String name = "告诉你啊";
    String phone = "15951618988";
    expressCompanyService.updateByExample(id,name,phone);
  }


}


