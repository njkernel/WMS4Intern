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
    public static final String INIT_STATUS = "wait";
    public static final String OVER_STATUS = "over";
    public static final String FAIL_STATUS = "fail";
    public static final String SUCCESS_STATUS = "success";
    public static final String RECEIVING_REPERTORY = "nanjing";
    public static final String SYNC_FALSE_STATES = "false";
    public static final String SYNC_TRUE_STATES = "true";
    public static final String REVISER = "WMS";
    public static final String TOKENS = "yonyong";
    public static final String PUSH_URL = "http://127.0.0.1:8502/Api/getReturnInputFeedback";
    public static final String GOODS_TOTAL_URL = "http://127.0.0.1:8502/updateTotalStock";
    public static final String GOODS_UPDATE_URL = "http://127.0.0.1:8502/updateGoods";
    public static final String OUT_UPDATE_URL = "http://127.0.0.1:8502/synchronizeState";
    public static final String CANCEL_OUT_URL = "http://127.0.0.1:8502/cancelOrderOfWms";
    public static final Integer upperLimit = 1000000;
}
