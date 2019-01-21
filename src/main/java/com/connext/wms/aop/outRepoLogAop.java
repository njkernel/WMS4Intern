package com.connext.wms.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author xiamingxing
 * @date 2019/1/21 9:23
 */

@Aspect
@Component
@Slf4j
public class OutRepoLogAop {

    @Pointcut("@annotation(com.connext.wms.aop.OutRepoAnnotation)")
    public void cutMethod() {
    }

    @Before(value="@annotation(outRepoAnnotation)")
    public void beforeMethod(JoinPoint joinPoint,OutRepoAnnotation outRepoAnnotation){
        log.info("[before] module name:{}, method name:{}，method param:{}", outRepoAnnotation.value(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

}
