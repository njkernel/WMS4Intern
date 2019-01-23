package com.connext.wms.api.dto;

import com.connext.wms.util.ToMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Marcus
 * @Date: 2019/1/8 9:41
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@Builder
public class InRepertoryDTO extends ToMap {
    private String tokens;
    private String inRepoId;
    private String orderId;
    private String channelId;
    private String expressCompany;
    private String expressId;
}

