package com.connext.wms.api.dto;

import com.connext.wms.util.ToMap;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InRepertoryDetailDTO extends ToMap {
    private String sku;
    private Integer goodsNum;
}
