package com.connext.wms.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Marcus
 * @Date: 2019/1/10 16:46
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class InputFeedbackDetail {
    private String goodsSku;
    private String goodsNum;
}
