package com.connext.wms.util;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: Marcus
 * @Date: 2019/1/9 10:36
 * @Version 1.0
 */
@Component
public class Constant {
    public final String INIT_STATUS = "wait";
    public final String OVER_STATUS = "over";
    public final String FAIL_STATUS = "fail";
    public final String SUCCESS_STATUS = "success";
    public final String SYNC_FALSE_STATES = "false";
    public final String SYNC_TRUE_STATES = "true";
    public final String RECEIVING_REPERTORY = "nanjing";
    public final String REVISER = "WMS";
    public final String TOKENS = "yonyong";
    public final String PUSH_URL = "http://127.0.0.1:8502/Api/getReturnInputFeedback";
    public final String GOODS_TOTAL_URL = "http://127.0.0.1:8502/updateTotalStock";
    public final String GOODS_UPDATE_URL = "http://127.0.0.1:8502/updateGoods";
    public final String OUT_UPDATE_URL = "http://127.0.0.1:8502/synchronizeState";
    public final String CANCEL_OUT_URL = "http://127.0.0.1:8502/cancelOrderOfWms";
}
