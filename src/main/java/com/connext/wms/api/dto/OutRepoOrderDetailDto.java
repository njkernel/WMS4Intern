package com.connext.wms.api.dto;

import com.connext.wms.util.ToMap;
import lombok.Data;

/**
 * @author xiamingxing
 * @date 2019/1/10 11:58
 */
@Data
public class OutRepoOrderDetailDto extends ToMap {
    private String goodsCode;
    private Integer num;

    public OutRepoOrderDetailDto(String goodsCode, Integer num) {
        this.goodsCode = goodsCode;
        this.num = num;
    }
}
