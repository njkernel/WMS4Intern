package com.connext.wms.api.dto;

import com.connext.wms.util.ToMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:18
 * @Version 1.0 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class InRepertoryDetailDTO extends ToMap {
    private String sku;
    private Integer goodsNum;
}
