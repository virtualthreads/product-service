package com.aeropelican.productservice.annotations.aspect;

import com.aeropelican.productservice.annotations.MethodTriggerLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MethodTriggerAspect {

    @Around("@annotation(methodTriggerLog)")
    public Object logMethodTrigger(ProceedingJoinPoint proceedingJoinPoint, MethodTriggerLog methodTriggerLog) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String classname = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        log.info("Request has been received by {}::{}", classname, methodName);
        return proceedingJoinPoint.proceed();
    }
}
