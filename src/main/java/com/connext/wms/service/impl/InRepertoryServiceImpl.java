package com.connext.wms.service.impl;

import com.connext.wms.api.dto.InputFeedback;
import com.connext.wms.api.dto.InputFeedbackDetail;
import com.connext.wms.api.util.EntityAndDto;
import com.connext.wms.dao.InRepertoryDetailMapper;
import com.connext.wms.dao.InRepertoryMapper;
import com.connext.wms.entity.InRepertory;
import com.connext.wms.entity.InRepertoryDetailExample;
import com.connext.wms.entity.InRepertoryExample;
import com.connext.wms.service.InRepertoryService;
import com.connext.wms.service.RepertoryRegulationService;
import com.connext.wms.util.AES;
import com.connext.wms.util.Constant;
import com.connext.wms.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2019/1/7 10:15
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InRepertoryServiceImpl implements InRepertoryService {
    private final InRepertoryMapper inRepertoryMapper;
    private final InRepertoryDetailMapper inRepertoryDetailMapper;
    private final Constant constant;
    private final EntityAndDto entityAndDto;
    private final RestTemplate restTemplate;
    private final RepertoryRegulationService regulationService;
    private final String PUSH_URL = "http://10.129.100.65:8502/Api/getExchangeInputFeedback";


    @Autowired
    public InRepertoryServiceImpl(InRepertoryMapper inRepertoryMapper, InRepertoryDetailMapper inRepertoryDetailMapper, Constant constant, EntityAndDto entityAndDto, RestTemplate restTemplate, RepertoryRegulationService regulationService) {
        this.inRepertoryMapper = inRepertoryMapper;
        this.inRepertoryDetailMapper = inRepertoryDetailMapper;
        this.constant = constant;
        this.entityAndDto = entityAndDto;
        this.restTemplate = restTemplate;
        this.regulationService = regulationService;
    }

    @Override
    public List<InRepertory> findAll() {
        InRepertoryExample example = new InRepertoryExample();
        example.setOrderByClause("'revise_time' DESC");
        return inRepertoryMapper.selectByExample(new InRepertoryExample());
    }

    @Override
    public List<InRepertory> findAllLike(String like) {
        InRepertoryExample example = new InRepertoryExample();
        example.or().andInRepoIdLike(like);
        example.or().andOrderIdLike(like);
        example.or().andExpressIdLike(like);
        return inRepertoryMapper.selectByExample(example);
    }

    @Override
    public List<InRepertory> findPage(Integer start, Integer size) {
        int pageStart = (start - 1) * size > 0 ? ((start - 1) * size) : 0;
        return inRepertoryMapper.getPage(pageStart, size);
    }

    @Override
    public List<InRepertory> findPageBy(String status, Integer start, Integer size) {
        int pageStart = (start - 1) * size > 0 ? ((start - 1) * size) : 0;
        return inRepertoryMapper.getPageBy(status, pageStart, size);
    }

    @Override
    public InRepertory findOne(Integer id) {
        InRepertory inRepertory = inRepertoryMapper.selectByPrimaryKey(id);
        InRepertoryDetailExample detailExample = new InRepertoryDetailExample();
        detailExample.or().andInRepoIdEqualTo(Integer.valueOf(inRepertory.getInRepoId()));
        inRepertory.setRepertoryDetails(inRepertoryDetailMapper.selectByExample(detailExample));
        return inRepertory;
    }

    @Override
    public void initInRepertory(InRepertory inRepertory) {
        inRepertoryMapper.insert(inRepertory);
        inRepertory.getRepertoryDetails().forEach(inRepertoryDetailMapper::insert);
    }

    @Override
    public List<InRepertory> checkInRepertoryExpired(List<InRepertory> inRepertories) {
        List<InRepertory> expires = new ArrayList<>();
        inRepertories.forEach(i -> {
            if (constant.INIT_STATUS.equals(i.getInRepoStatus()) && isExpired(i)) {
                expires.add(i);
            }
        });
        expires.forEach(u -> changeInRepertoryStatus(u.getId(), constant.OVER_STATUS));
        return expires;
    }

    @Override
    public boolean changeInRepertoryStatus(Integer id, String status) {
        InRepertory inRepertory = new InRepertory();
        inRepertory.setId(id);
        inRepertory.setReviseTime(new Date());
        inRepertory.setInRepoStatus(status);
        inRepertory.setSyncStatus(constant.SYNC_TRUE_STATES);
        if (constant.INIT_STATUS.equals(inRepertoryMapper.selectByPrimaryKey(id).getInRepoStatus())) {
            inRepertoryMapper.updateByPrimaryKeySelective(inRepertory);
//            if(status.equals(constant.SUCCESS_STATUS)){
//                inRepertory.getRepertoryDetails().forEach(
//                        //增加库存
//                        u->regulationService.rejectedGoodsSuccess(u.getGoodsId(),u.getGoodsNum())
//                );
//            }
            return true;
        }
        return false;
    }

    @Override
    public boolean pushInRepertoryState(InRepertory inRepertory) {
        List<InputFeedbackDetail> list = entityAndDto.toDTO(inRepertory);
        InputFeedback inputFeedback = new InputFeedback(AES.AESEncode(constant.TOKENS, inRepertory.getOrderId()), Integer.valueOf(inRepertory.getOrderId()), inRepertory.getInRepoStatus(), list);
        try {
            restTemplate.postForObject(PUSH_URL, inputFeedback.toMap(), String.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Page getPageInfo(Integer page, List<InRepertory> inRepertoryList, String status) {
        Page pageModel = new Page();
        pageModel.setCurrPage(page);
        pageModel.setData(inRepertoryList);
        InRepertoryExample example = new InRepertoryExample();
        long count = 0;
        if (status.equals("")) {
            count = inRepertoryMapper.countByExample(example);
        } else {
            example.or().andInRepoStatusEqualTo(status);
            count = inRepertoryMapper.countByExample(example);
        }
        pageModel.setTotalCount(count);
        pageModel.init();
        return pageModel;
    }

    /**
     * if InRepertory over 15 days then return true
     *
     * @param inRepertory InRepertory
     * @return boolean
     */
    boolean isExpired(InRepertory inRepertory) {
        double days = 1.296E9;
        long creatTime = inRepertory.getCreateTime().getTime();
        long now = System.currentTimeMillis();
        return now - creatTime > days;
    }
}
