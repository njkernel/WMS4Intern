package com.connext.wms;

import com.connext.wms.dao.GoodsMapper;
import com.connext.wms.dao.UserMapper;
import com.connext.wms.entity.GoodsExample;
import com.connext.wms.entity.Goods;
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

  @Test
  public void contextLoads() {
//    goodsMapper.insert(new Goods(1, "1", "1", 1F));
//    goodsMapper.insert(new Goods(2, "2", "2", 1F));
    GoodsExample goodsExample = new GoodsExample();
    System.out.println(goodsMapper.countByExample(goodsExample));
  }
}
