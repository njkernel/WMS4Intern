package com.connext.wms.service.impl;

import com.connext.wms.api.dto.InRepertoryDetailDTO;
import com.connext.wms.api.dto.InputFeedback;
import com.connext.wms.api.dto.InputFeedbackDetail;
import com.connext.wms.api.util.EntityAndDto;
import com.connext.wms.dao.InRepertoryDetailMapper;
import com.connext.wms.dao.InRepertoryMapper;
import com.connext.wms.entity.InRepertory;
import com.connext.wms.entity.InRepertoryDetail;
import com.connext.wms.entity.InRepertoryDetailExample;
import com.connext.wms.entity.InRepertoryExample;
import com.connext.wms.service.InRepertoryService;
import com.connext.wms.service.RepertoryRegulationService;
import com.connext.wms.util.AES;
import com.connext.wms.util.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Marcus
 * @Date: 2019/1/7 10:15
 * @Version 1.0
 */
@Service
@Slf4j
public class InRepertoryServiceImpl implements InRepertoryService {
    private final InRepertoryMapper inRepertoryMapper;
    private final InRepertoryDetailMapper inRepertoryDetailMapper;
    private final EntityAndDto entityAndDto;
    private final RestTemplate restTemplate;
    private final RepertoryRegulationService regulationService;


    @Autowired
    public InRepertoryServiceImpl(InRepertoryMapper inRepertoryMapper, InRepertoryDetailMapper inRepertoryDetailMapper, EntityAndDto entityAndDto, RestTemplate restTemplate, RepertoryRegulationService regulationService) {
        this.inRepertoryMapper = inRepertoryMapper;
        this.inRepertoryDetailMapper = inRepertoryDetailMapper;
        this.entityAndDto = entityAndDto;
        this.restTemplate = restTemplate;
        this.regulationService = regulationService;
    }

    @Override
    public List<InRepertory> findAllWait() {
        InRepertoryExample example = new InRepertoryExample();
        example.or().andInRepoStatusEqualTo(Constant.INIT_STATUS);
        return inRepertoryMapper.selectByExample(example);
    }

    @Override
    public PageInfo findAllLike(String status, String like, int pageNum, int size) {
        String likeSth = "%" + like + "%";
        PageHelper.startPage(pageNum, size);
        List<InRepertory> list = inRepertoryMapper.findAllLike(status, likeSth);
        return new PageInfo(list);
    }

    @Override
    public PageInfo findPage(Integer start, Integer size) {
        InRepertoryExample example = new InRepertoryExample();
        example.setOrderByClause("revise_time DESC");
        PageHelper.startPage(start, size);
        List<InRepertory> list = inRepertoryMapper.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public PageInfo findPageBy(String status, Integer start, Integer size) {
        InRepertoryExample example = new InRepertoryExample();
        example.setOrderByClause("revise_time DESC");
        example.or().andInRepoStatusEqualTo(status);
        PageHelper.startPage(start, size);
        List<InRepertory> list = inRepertoryMapper.selectByExample(example);
        return new PageInfo(list);
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
    @Transactional(rollbackFor = Exception.class)
    public void initInRepertory(InRepertory inRepertory) {
        inRepertoryMapper.insert(inRepertory);
        inRepertory.getRepertoryDetails().forEach(inRepertoryDetailMapper::insert);
    }

    @Override
    public List<InRepertory> checkInRepertoryExpired(List<InRepertory> inRepertories) {
        List<InRepertory> expires = new ArrayList<>();
        inRepertories.forEach(i -> {
            if (Constant.INIT_STATUS.equals(i.getInRepoStatus()) && isExpired(i)) {
                expires.add(findOne(i.getId()));
            }
        });
        expires.forEach(u -> changeInRepertoryStatus(u.getId(), Constant.OVER_STATUS));
        return expires;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeInRepertoryStatus(Integer id, String status) {
        InRepertory inRepertory = InRepertory.builder().id(id).reviseTime(new Date()).inRepoStatus(status).syncStatus(Constant.SYNC_TRUE_STATES).build();
        if (Constant.INIT_STATUS.equals(inRepertoryMapper.selectByPrimaryKey(id).getInRepoStatus())) {
            inRepertoryMapper.updateByPrimaryKeySelective(inRepertory);
            if (status.equals(Constant.SUCCESS_STATUS)) {
                InRepertoryDetailExample detailExample = new InRepertoryDetailExample();
                detailExample.or().andInRepoIdEqualTo(Integer.valueOf(inRepertoryMapper.selectByPrimaryKey(id).getInRepoId()));
                inRepertoryDetailMapper.selectByExample(detailExample).forEach(
                        u -> regulationService.rejectedGoodsSuccess(u.getGoodsId(), u.getGoodsNum())
                );
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean pushInRepertoryState(InRepertory inRepertory) {
        List<InputFeedbackDetail> list = entityAndDto.toDTO(inRepertory);
        InputFeedback inputFeedback = new InputFeedback(AES.AESEncode(Constant.TOKENS, inRepertory.getOrderId()), Integer.valueOf(inRepertory.getOrderId()), inRepertory.getInRepoStatus(), list);
        try {
            restTemplate.postForObject(Constant.PUSH_URL, inputFeedback.toMap(), String.class);
            log.info("入库单返回状态推送成功：" + inputFeedback.getOrderId());
            return true;
        } catch (Exception e) {
            log.info("入库单返回状态推送失败：" + inputFeedback.getOrderId());
            return false;
        }
    }

    @Override
    public int changeStatusAndPush(List<Integer> ids, String status) {
        AtomicInteger rows = new AtomicInteger();
        ids.forEach(
                u -> {
                    if (changeInRepertoryStatus(u, status)) {
                        rows.addAndGet(1);
                    }
                    pushInRepertoryState(findOne(u));
                }
        );
        return rows.get();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean actionException(int id, List<InRepertoryDetail> inRepertoryDetail) {
        InRepertory inRepertory = findOne(id);
        for (int i = 0; i < inRepertoryDetail.size(); i++) {
            if (0 <= inRepertoryDetail.get(i).getGoodsNum() && inRepertoryDetail.get(i).getGoodsNum() <= inRepertory.getRepertoryDetails().get(i).getGoodsNum()) {
            } else {
                return false;
            }
        }
        boolean result = changeInRepertoryStatus(id, Constant.SUCCESS_STATUS);
        inRepertoryDetail.forEach(inRepertoryDetailMapper::updateByPrimaryKey);
        if (result) {
            inRepertory.setInRepoStatus(Constant.SUCCESS_STATUS);
            pushInRepertoryState(inRepertory);
            return true;
        }
        return false;
    }

    /**
     * if InRepertory over 15 days then return true
     *
     * @param inRepertory InRepertory
     * @return boolean
     */
    private boolean isExpired(InRepertory inRepertory) {
        double days = 1.296E9;
        long creatTime = inRepertory.getCreateTime().getTime();
        long now = System.currentTimeMillis();
        return now - creatTime > days;
    }
}
