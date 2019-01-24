package com.connext.wms.service.impl;

import com.connext.wms.dao.InRepertoryDetailMapper;
import com.connext.wms.dao.InRepertoryMapper;
import com.connext.wms.entity.InRepertory;
import com.connext.wms.entity.InRepertoryDetail;
import com.connext.wms.entity.InRepertoryDetailExample;
import com.connext.wms.entity.InRepertoryExample;
import com.connext.wms.service.InRepertoryService;
import com.connext.wms.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2019/1/22 11:01
 * @Version 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class InRepertoryServiceImplTest {
    @Autowired
    InRepertoryService inRepertoryService;
    @Autowired
    InRepertoryMapper inRepertoryMapper;
    @Autowired
    InRepertoryDetailMapper inRepertoryDetailMapper;
    @Autowired
    Constant constant;

    @Test
    void clean() {
        inRepertoryMapper.deleteByExample(new InRepertoryExample());
        inRepertoryDetailMapper.deleteByExample(new InRepertoryDetailExample());
    }


    @Test
    void initState() {
        InRepertory inRepertory = new InRepertory();
        inRepertory.setInRepoStatus(constant.INIT_STATUS);
        inRepertory.setSyncStatus(constant.SYNC_FALSE_STATES);
        inRepertoryMapper.updateByExampleSelective(inRepertory, new InRepertoryExample());
    }

    @Test
    void findAllWait() {
        inRepertoryService.findAllWait().forEach(System.out::println);
    }

    @Test
    void findAllLike() {
        inRepertoryService.findAllLike(null, "3", 1, 10).getList().forEach(System.out::println);
    }

    @Test
    void findPage() {
        inRepertoryService.findPage(1, 10).getList().forEach(System.out::println);
    }

    @Test
    void findPageBy() {
        inRepertoryService.findPageBy("wait", 1, 10).getList().forEach(System.out::println);
    }

    @Test
    void findOne() {
        inRepertoryService.findOne(17);
    }

    @Test
    void initInRepertory() {
        inRepertoryService.initInRepertory(inRepertoryService.findOne(17));
    }

    @Test
    void checkInRepertoryExpired() {
        inRepertoryService.checkInRepertoryExpired(inRepertoryService.findPage(1,10).getList());
    }

    @Test
    void changeInRepertoryStatus() {
        inRepertoryService.changeInRepertoryStatus(17,"over");
        InRepertory inRepertory=inRepertoryService.findOne(17);
        assertEquals("over", inRepertory.getInRepoStatus());
    }

    @Test
    void pushInRepertoryState() {
    }

    @Test
    void changeStatusAndPush() {
    }

    @Test
    void actionException() {
    }
}