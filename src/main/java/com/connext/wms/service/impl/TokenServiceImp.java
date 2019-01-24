package com.connext.wms.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.connext.wms.dao.TokenManageMapper;
import com.connext.wms.entity.TokenManage;
import com.connext.wms.entity.TokenManageExample;
import com.connext.wms.service.TokenService;
import com.connext.wms.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiamingxing
 * @date 2019/1/24 1:13
 */
@Service
public class TokenServiceImp implements TokenService {
    @Autowired
    private TokenManageMapper tokenManageMapper;

    @Override
    public String getToken(String omsname, String password) {
        String token = "";
        token = JwtTokenUtils.createToken(omsname, password);
        return token;
    }

    @Override
    public TokenManage getTokenAccount(String omsname) {
        return this.tokenManageMapper.selectByOmsname(omsname);
    }

}
