package com.aeropelican.productservice.annotations.aspect;

import com.aeropelican.productservice.annotations.MethodExecutionTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MethodExecutionTimeAspect {

    @Around("@annotation(methodExecutionTime)")
    public Object calculateExecutionTime(
            ProceedingJoinPoint proceedingJoinPoint,
            MethodExecutionTime methodExecutionTime) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;

        log.info("Total execution time is {} milliseconds", executionTime);

        return result;
    }
}