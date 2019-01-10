package com.connext.wms.util;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: Marcus
 * @Date: 2019/1/9 10:36
 * @Version 1.0
 */
@Data
@Component
public class Constant {
    private final String INIT_STATUS = "wait";
    private final String OVER_STATUS = "over";
    private final String FAIL_STATUS = "fail";
    private final String SUCCESS_STATUS = "success";
    private final String SYNC_FALSE_STATES = "false";
    private final String SYNC_TRUE_STATES = "true";
    private final String RECEIVING_REPERTORY = "nanjing";
    private final String REVISER="Marcus";
    private final String TOKENS="yonyong";
}
