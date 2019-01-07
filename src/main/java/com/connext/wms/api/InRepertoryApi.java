package com.connext.wms.api;

import com.connext.wms.entity.InRepertory;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Marcus
 * @Date: 2019/1/7 10:44
 * @Version 1.0
 */
@RestController
public class InRepertoryApi {
    public InRepertory inRepertoryOrder(InRepertory inRepertory) {
        return inRepertory;
    }
}
