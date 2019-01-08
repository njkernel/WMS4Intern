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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WmsApplicationTests {
  @Autowired UserMapper userMapper;
  @Autowired GoodsMapper goodsMapper;
  @Autowired private ExpressCompanyMapper expressCompanyMapper;
  @Autowired private OutRepertoryMapper outRepertoryMapper;
  @Autowired private ExceptionService exceptionService;
  @Autowired private ExpressCompanyService expressCompanyService;


  @Test
  public void contextLoads() {
//    goodsMapper.insert(new Goods(1, "1", "1", 1F));
//    goodsMapper.insert(new Goods(2, "2", "2", 1F));
    GoodsExample goodsExample = new GoodsExample();
    System.out.println(goodsMapper.countByExample(goodsExample));
  }

  @Test
  public void insert(){
    String expressCompanyName = "顺丰快递公司";
    String contactWay = "2222222222";
    ExpressCompany expressCompany = new ExpressCompany();
    expressCompany.setExpressCompanyName(expressCompanyName);
    expressCompany.setContactWay(contactWay);
    expressCompanyMapper.insert(expressCompany);
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
    String key = "%" + "顺丰" + "%";
    ExpressCompanyExample example = new ExpressCompanyExample();
    example.createCriteria().andExpressCompanyNameLike(key);
    System.out.println(expressCompanyMapper.selectByExample(example));
  }

  @Test
  //查找所有异常订单
  public void selectByExample2(){
    String Key = "%异常%";
    OutRepertoryExample example = new OutRepertoryExample();
    example.createCriteria().andOutRepoStatusLike(Key);
    System.out.println(outRepertoryMapper.selectByExample(example));
  }

  @Test
  //查询所有快递公司信息(分页)(pageHelper)
  public void selectByExample3(){
    int pageNum = 2;
    int pageSize = 1;
    PageHelper.startPage(pageNum,pageSize);
    ExpressCompanyExample example = new ExpressCompanyExample();
    List<ExpressCompany> expressCompanies = expressCompanyMapper.selectByExample(example);
    PageInfo<ExpressCompany> appsPageInfo = new PageInfo<>(expressCompanies);
    System.out.println(appsPageInfo);
  }

  @Test
  //分页查询快递公司信息
  public void findByPage(){
    System.out.println(expressCompanyService.selectByPage(1,2));
  }

  @Test
  //分页查询异常信息
  public void selectByPage(){
    System.out.println(exceptionService.selectByPage(1,2));
  }

}
