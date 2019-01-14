package com.connext.wms.service.impl;

import com.connext.wms.dao.InRepertoryMapper;
import com.connext.wms.service.InRepertoryService;
import com.connext.wms.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2019/1/7 15:46
 * @Version 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
class InRepertoryServiceImplTest {
    @Autowired
    InRepertoryService inRepertoryService;
    @Autowired
    Constant constant;
    @Autowired
    InRepertoryMapper inRepertoryMapper;

    @Test
    public void init() {
        inRepertoryService.findAll().forEach(
                u ->{
                    u.setSyncStatus(constant.SYNC_FALSE_STATES);
                    u.setInRepoStatus(constant.INIT_STATUS);
                    inRepertoryMapper.updateByPrimaryKeySelective(u);
                }
        );
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllLike() {
    }

    @Test
    void findPage() {
    }

    @Test
    void findPageBy() {
        inRepertoryService.findPageBy("success", 0, 5).forEach(System.out::println);
    }

    @Test
    void findOne() {
        inRepertoryService.findAll().forEach(System.out::println);
    }

    @Test
    void initInRepertory() {
    }

    @Test
    void checkInRepertoryExpired() {
    }

    @Test
    void changeInRepertoryStatus() {
        inRepertoryService.changeInRepertoryStatus(47, "success");
        inRepertoryService.changeInRepertoryStatus(50, "success");
    }

    @Test
    void pushInRepertoryState() {
    }

    @Test
    void isExpired() {
    }
}