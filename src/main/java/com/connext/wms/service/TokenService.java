package com.connext.wms.service;

import com.connext.wms.entity.TokenManage;

/**
 * @author xiamingxing
 * @date 2019/1/24 1:12
 */
public interface TokenService {
    /**
     * 创建token
     * @return
     */
    public String getToken(String omsname, String password);

    /**
     * 根据oms账号密码获取记录
     * @param omsname
     * @return
     */
    public TokenManage getTokenAccount(String omsname);

}
