package com.connext.wms.config;

import com.connext.wms.service.GoodsRepertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/10 10:15
 */
@Component
public class ScheduledTask {

    private final
    GoodsRepertoryService goodsRepertoryService;

    @Autowired
    public ScheduledTask(GoodsRepertoryService goodsRepertoryService) {
        this.goodsRepertoryService = goodsRepertoryService;
    }

    /**
     * 定时检查入库超时，每日凌晨4点，每10分钟检查一次
     */
    @Scheduled(cron = "0 0 4 ? * *")
    public void reportCurrentTime() {
        goodsRepertoryService.updateGoodsRepertory();
    }
}
