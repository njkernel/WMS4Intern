/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.connext.wms.config;

import java.util.List;

import com.connext.wms.entity.InRepertory;
import com.connext.wms.service.GoodsRepertoryService;
import com.connext.wms.service.InRepertoryService;
import com.connext.wms.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: Marcus
 * @Date: 2019/1/7 10:02
 * @Version 1.0
 */
@Component
public class ScheduledTasks {
    private final InRepertoryService inRepertoryService;
    private final GoodsRepertoryService goodsRepertoryService;
    private final Constant constant;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    public ScheduledTasks(InRepertoryService inRepertoryService, GoodsRepertoryService goodsRepertoryService, Constant constant) {
        this.inRepertoryService = inRepertoryService;
        this.goodsRepertoryService = goodsRepertoryService;
        this.constant = constant;
    }

    /**
     * 定时检查入库超时，每日凌晨4点，每10分钟检查一次
     */
    @Scheduled(cron = "0 0/10 4 ? * *")
    public void checkInRepertoryExpired() {
        List<InRepertory> inRepertories = inRepertoryService.checkInRepertoryExpired(inRepertoryService.findAll());
        //推送通知
        log.info("收货超时" + inRepertories.toString());
        inRepertories.forEach(u -> {
            inRepertoryService.changeInRepertoryStatus(u.getId(), constant.OVER_STATUS);
            inRepertoryService.pushInRepertoryState(u);
        });
    }

    /**
     * 定时检查入库超时，每日凌晨4点，每10分钟检查一次
     */
    @Scheduled(cron = "0 0/10 4 ? * *")
    public void updateGoodsRepertory() {
        goodsRepertoryService.updateGoodsRepertory();
    }
}