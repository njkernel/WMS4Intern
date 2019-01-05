package com.connext.wms;

import com.connext.wms.dao.UserMapper;
import com.connext.wms.entity.User;
import com.connext.wms.entity.UserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WmsApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
    System.out.println(userMapper.insert(new User(1,"1","1")));
    }

}

