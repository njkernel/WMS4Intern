package com.connext.wms.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: Marcus
 * @Date: 2019/1/9 13:49
 * @Version 1.0
 */
@Component
@Aspect
@Slf4j
public class InRepertoryInfo {
    @Pointcut("execution(* com.connext.wms.api.InRepertoryApi.inRepertoryOrder(..))")
    private void inRepo() {
    }
    @After("inRepo()")
    public void info() {
        log.info("InRepertoryApi has receive a message");
    }
}
