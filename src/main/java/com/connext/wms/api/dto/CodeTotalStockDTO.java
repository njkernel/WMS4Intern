package com.connext.wms.api.dto;

import lombok.Data;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/9 14:14
 */
@Data
public class CodeTotalStockDTO {
    private String goodsCode;
    private Integer totalStock;

    public CodeTotalStockDTO(String goodsCode, Integer totalStock) {
        this.goodsCode = goodsCode;
        this.totalStock = totalStock;
    }

    public CodeTotalStockDTO() {
    }
}
