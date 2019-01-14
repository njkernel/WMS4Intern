package com.connext.wms.api.dto;

import com.connext.wms.util.ToMap;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/8
 * @time: 21:31
 * @describe: 退换货Dto
 **/
@Data
@AllArgsConstructor
public class InputFeedback extends ToMap {
    private String tokens;
    private int orderId;
    private String inputState;
    private List<InputFeedbackDetail> goodDetails;
}
