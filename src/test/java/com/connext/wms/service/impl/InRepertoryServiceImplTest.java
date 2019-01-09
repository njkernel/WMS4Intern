package com.connext.wms.service.impl;

import com.connext.wms.entity.InRepertory;
import com.connext.wms.service.InRepertoryService;
import com.connext.wms.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
class InRepertoryServiceImplTest {
    @Autowired
    InRepertoryService inRepertoryService;
    @Autowired
    Constant constant;
    InRepertory inRepertory=new InRepertory(null,"123456","1234567","12345678","1234569","顺丰速运","","","南京",new Date(),"Marcus",new Date());

    @Test
    void findAll() {
        inRepertoryService.findAll().forEach(System.out::print);
    }

    @Test
    void findOne() {
        log.info(inRepertoryService.findOne(1).toString());
    }

    @Test
    void initInRepertory() {
        inRepertory.setRepertoryDetails(new ArrayList<>());
        inRepertoryService.initInRepertory(inRepertory);
    }

    @Test
    void checkInRepertoryExpired() {
        List<InRepertory> inRepertories= inRepertoryService.findAll();
        inRepertoryService.checkInRepertoryExpired(inRepertories).forEach(System.out::print);
    }

    @Test
    void changeInRepertoryStatus() {
        inRepertoryService.changeInRepertoryStatus(4,constant.getSUCCESS_STATUS());
    }

    @Test
    void pushInRepertoryState() {
    }
    @Test
    void findAllLike() {
        inRepertoryService.findAllLike("%1234569%").forEach(System.out::println);
    }
}