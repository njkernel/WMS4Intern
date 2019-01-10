package com.connext.wms.service.impl;

import com.connext.wms.dao.InRepertoryDetailMapper;
import com.connext.wms.dao.InRepertoryMapper;
import com.connext.wms.entity.InRepertory;
import com.connext.wms.entity.InRepertoryDetailExample;
import com.connext.wms.entity.InRepertoryExample;
import com.connext.wms.service.InRepertoryService;
import com.connext.wms.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Autowired
    public InRepertoryServiceImpl(InRepertoryMapper inRepertoryMapper, InRepertoryDetailMapper inRepertoryDetailMapper, Constant constant) {
        this.inRepertoryMapper = inRepertoryMapper;
        this.inRepertoryDetailMapper = inRepertoryDetailMapper;
        this.constant = constant;
    }

    @Override
    public List<InRepertory> findAll() {
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
    public List<InRepertory> findAllPage(Integer start, Integer size) {
        int pageStart = (start - 1) * size > 0 ? ((start - 1) * size) : 0;
        return inRepertoryMapper.getAllPage(pageStart, size);
    }

    @Override
    public List<InRepertory> findPage(Integer start, Integer size) {
        int pageStart = (start - 1) * size > 0 ? ((start - 1) * size) : 0;
        return inRepertoryMapper.getPage(pageStart, size);
    }

    @Override
    public InRepertory findOne(Integer id) {
        InRepertoryDetailExample detailExample = new InRepertoryDetailExample();
        detailExample.createCriteria().andInRepoIdEqualTo(id);
        InRepertory inRepertory = inRepertoryMapper.selectByPrimaryKey(id);
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
            if (constant.getINIT_STATUS().equals(i.getInRepoStatus()) && isExpired(i)) {
                expires.add(i);
            }
        });
        expires.forEach(u -> changeInRepertoryStatus(u.getId(), constant.getOVER_STATUS()));
        return expires;
    }

    @Override
    public void changeInRepertoryStatus(Integer id, String status) {
        InRepertory inRepertory = new InRepertory();
        inRepertory.setId(id);
        inRepertory.setReviseTime(new Date());
        inRepertory.setInRepoStatus(status);
        inRepertory.setSyncStatus(constant.getSYNC_TRUE_STATES());
        inRepertoryMapper.updateByPrimaryKeySelective(inRepertory);
        InRepertory repertory=findOne(id);
        //发送通知
        repertory.getRepertoryDetails();
    }

    @Override
    public boolean pushInRepertoryState(InRepertory inRepertory, String remark) {
        //推送
        return true;
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
