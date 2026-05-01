package com.fundoo.notes.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.fundoo.notes.controller..*(..))")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().toShortString();

        log.info(" Request started: {}", methodName);

        Object result;

        try {
            result = joinPoint.proceed();
        } catch (Exception ex) {
            log.error(" Exception in {}: {}", methodName, ex.getMessage());
            throw ex;
        }

        long timeTaken = System.currentTimeMillis() - start;

        log.info(" Response from {} in {} ms", methodName, timeTaken);

        return result;
    }
}