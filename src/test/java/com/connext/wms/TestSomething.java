package com.connext.wms;

import com.connext.wms.api.dto.InRepertoryDetailDTO;
import com.connext.wms.api.dto.InputFeedback;
import com.connext.wms.api.util.EntityAndDto;
import com.connext.wms.dao.InRepertoryDetailMapper;
import com.connext.wms.dao.InRepertoryMapper;
import com.connext.wms.entity.InRepertoryDetail;
import com.connext.wms.entity.InRepertoryDetailExample;
import com.connext.wms.util.AES;
import com.connext.wms.entity.InRepertory;
import com.connext.wms.service.InRepertoryService;
import com.connext.wms.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2019/1/7 9:45
 * @Version 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSomething {
    @Autowired
    InRepertoryService inRepertoryService;
    @Autowired
    InRepertoryMapper inRepertoryMapper;
    @Autowired
    InRepertoryDetailMapper inRepertoryDetailMapper;
    @Autowired
    EntityAndDto entityAndDto;
    @Autowired
    Constant constant;

    @Test
    public void push() {
        RestTemplate restTemplate = new RestTemplate();
        Integer id = 36;
        InRepertory inRepertory = inRepertoryService.findOne(id);
        List<InRepertoryDetailDTO> list = entityAndDto.toDTO(inRepertory);
        InputFeedback inputFeedback = new InputFeedback(AES.AESEncode(constant.getTOKENS(), inRepertory.getOrderId()), Integer.valueOf(inRepertory.getOrderId()), "收货失败", list);
        log.info(restTemplate.postForObject("http://10.129.100.65:8502/Api/getExchangeInputFeedback", inputFeedback.toMap(), String.class));
}

    @Test
    public void t() {
        InRepertory inRepertory = inRepertoryMapper.selectByPrimaryKey(4);
        InRepertoryDetailExample detailExample = new InRepertoryDetailExample();
        Integer id = Integer.valueOf(inRepertory.getInRepoId());
        detailExample.or().andInRepoIdEqualTo(id);
        inRepertoryDetailMapper.selectByExample(detailExample).forEach(System.out::println);
    }

    @Test
    public void test() {
        InRepertory inRepertory = inRepertoryService.findOne(5);
        double days = 1.296E9;
        long creatTime = inRepertory.getCreateTime().getTime();
        long now = System.currentTimeMillis();
        log.info(String.valueOf(now - creatTime > days));
    }
}
