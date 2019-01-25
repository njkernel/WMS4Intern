package com.connext.wms.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.connext.wms.entity.TokenManage;
import com.connext.wms.service.TokenService;
import com.connext.wms.util.JwtTokenUtils;
import com.connext.wms.util.PassToken;
import com.connext.wms.util.NeedToken;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author xiamingxing
 * @date 2019/1/24 0:48
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(NeedToken.class)) {
            NeedToken needToken = method.getAnnotation(NeedToken.class);
            if (needToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新操作");
                }
                // 获取 token 中的 user id
                String omsname;
                try {
                    omsname = JwtTokenUtils.getUsername(token);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("token格式有误");
                }
                TokenManage tokenManage = this.tokenService.getTokenAccount(omsname);
                if (tokenManage == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                String password = JwtTokenUtils.getpassword(token);
                System.out.println("^^^^"+password);
                System.out.println("*****"+tokenManage.getPassword());
                if(!tokenManage.getPassword().equals(password)){
                    throw new RuntimeException("用户密码错误");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
